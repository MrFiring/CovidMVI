package ru.mrfiring.covidmvi.domain

import com.badoo.mvicore.element.Actor
import io.reactivex.Observable
import io.reactivex.Observable.just
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.mrfiring.covidmvi.presentation.features.ContinentStatsFeature
import javax.inject.Inject

class ContinentStatsActor @Inject constructor(
    private val repository: CovidRepository
) : Actor<ContinentStatsFeature.State, ContinentStatsFeature.Wish, ContinentStatsFeature.Effect> {
    override fun invoke(
        state: ContinentStatsFeature.State,
        wish: ContinentStatsFeature.Wish
    ): Observable<ContinentStatsFeature.Effect> = when (wish) {
        is ContinentStatsFeature.Wish.LoadNewContinentStats -> {
            just(
                ContinentStatsFeature.Effect.ErrorLoading(
                    NotImplementedError("Not implemented.")
                )
            )
        }
        is ContinentStatsFeature.Wish.LoadCacheContinentStats -> {
            repository.getContinentStatsFromCache()
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapObservable {
                    just(ContinentStatsFeature.Effect.LoadedCacheGlobalStats(
                        it.map { stats ->
                            stats.asDomainGeneralStats()
                        }
                    ) as ContinentStatsFeature.Effect)
                }
                .startWith(just(ContinentStatsFeature.Effect.StartedLoading))
                .onErrorReturn { ContinentStatsFeature.Effect.ErrorLoading(it) }
        }
    }
}