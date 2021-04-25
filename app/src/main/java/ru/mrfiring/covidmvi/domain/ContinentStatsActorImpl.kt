package ru.mrfiring.covidmvi.domain

import com.badoo.mvicore.element.Actor
import io.reactivex.Observable
import io.reactivex.Observable.just
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.mrfiring.covidmvi.presentation.features.ContinentStatsFeature
import javax.inject.Inject

class ContinentStatsActorImpl @Inject constructor(
    private val repository: ContinentStatsRepository
) : Actor<ContinentStatsFeature.State, ContinentStatsFeature.Wish, ContinentStatsFeature.Effect> {
    override fun invoke(
        state: ContinentStatsFeature.State,
        wish: ContinentStatsFeature.Wish
    ): Observable<ContinentStatsFeature.Effect> = when (wish) {
        is ContinentStatsFeature.Wish.LoadNewContinentStats -> {
            repository.fetchContinentStats(state.sortType.str)
                .observeOn(AndroidSchedulers.mainThread())
                .startWith(just(ContinentStatsFeature.Effect.StartedLoading))
                .flatMap {
                    just(
                        ContinentStatsFeature.Effect.LoadedNewGlobalStats
                                as ContinentStatsFeature.Effect
                    )
                }
                .onErrorReturn {
                    ContinentStatsFeature.Effect.ErrorLoading(it)
                }
        }
        is ContinentStatsFeature.Wish.LoadCacheContinentStats -> {
            repository.getContinentStatsFromCache()
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapObservable {
                    just(ContinentStatsFeature.Effect.LoadedCacheGlobalStats(
                        it
                    ) as ContinentStatsFeature.Effect)
                }
                .startWith(just(ContinentStatsFeature.Effect.StartedLoading))
                .onErrorReturn { ContinentStatsFeature.Effect.ErrorLoading(it) }
        }

        is ContinentStatsFeature.Wish.SetSortType -> {
            just(
                ContinentStatsFeature.Effect.SetSortTypeEffect(
                    wish.sortType
                )
            )
        }
    }
}