package ru.mrfiring.covidmvi.domain

import com.badoo.mvicore.element.Actor
import io.reactivex.Observable
import io.reactivex.Observable.just
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.mrfiring.covidmvi.presentation.features.GlobalHistoricalStatsFeature
import javax.inject.Inject

class GlobalHistoricalStatsActorImpl @Inject constructor(
    private val repository: CovidRepository
): Actor<GlobalHistoricalStatsFeature.State, GlobalHistoricalStatsFeature.Wish, GlobalHistoricalStatsFeature.Effect> {
    override fun invoke(
        state: GlobalHistoricalStatsFeature.State,
        wish: GlobalHistoricalStatsFeature.Wish
    ): Observable<GlobalHistoricalStatsFeature.Effect> = when(wish) {
        is GlobalHistoricalStatsFeature.Wish.LoadNewHistoricalStats ->
            repository.fetchGlobalHistoricalStats(state.resolution)
                .observeOn(AndroidSchedulers.mainThread())
                .startWith(just(GlobalHistoricalStatsFeature.Effect.StartedLoading))
                .flatMap {
                    just(
                        GlobalHistoricalStatsFeature.Effect.LoadedNewHistoricalStats
                    as GlobalHistoricalStatsFeature.Effect
                    )
                }
                .onErrorReturn { GlobalHistoricalStatsFeature.Effect.ErrorLoading(it) }

        is GlobalHistoricalStatsFeature.Wish.LoadHistoricalStatsFromCache ->
            repository.getGlobalHistoricalStatsFromCache(state.resolution)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapObservable {
                    just(GlobalHistoricalStatsFeature.Effect.LoadedHistoricalStats(
                        it
                    ) as GlobalHistoricalStatsFeature.Effect)
                }
                .startWith(just(GlobalHistoricalStatsFeature.Effect.StartedLoading))
                .onErrorReturn { GlobalHistoricalStatsFeature.Effect.ErrorLoading(it) }

        is GlobalHistoricalStatsFeature.Wish.SetResolutionType -> just(
            GlobalHistoricalStatsFeature.Effect.SetResolutionType(
                wish.resolution
            )
        )
    }
}