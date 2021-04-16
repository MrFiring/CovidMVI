package ru.mrfiring.covidmvi.domain

import com.badoo.mvicore.element.Actor
import io.reactivex.Observable
import io.reactivex.Observable.just
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.mrfiring.covidmvi.presentation.features.GlobalStatsFeature
import javax.inject.Inject

class GlobalStatsActorImpl @Inject constructor(
    private val repository: CovidRepository
): Actor<GlobalStatsFeature.State, GlobalStatsFeature.Wish, GlobalStatsFeature.Effect>{

    override fun invoke(state: GlobalStatsFeature.State, wish: GlobalStatsFeature.Wish): Observable<GlobalStatsFeature.Effect> {
        return when(wish){
            is GlobalStatsFeature.Wish.LoadNewGlobalStats -> repository.fetchGlobalStats()
                .observeOn(AndroidSchedulers.mainThread())
                .startWith(just(GlobalStatsFeature.Effect.StartedLoading))
                .flatMap{
                    just(GlobalStatsFeature.Effect.LoadedNewGlobalStats as GlobalStatsFeature.Effect)
                }
                .onErrorReturn {
                    GlobalStatsFeature.Effect.ErrorLoading(it)
                }
            is GlobalStatsFeature.Wish.LoadGlobalStatsFromCache -> repository.getGlobalStatsLatestFromCache()
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapObservable {
                    just(GlobalStatsFeature.Effect.LoadedGlobalStats(it) as GlobalStatsFeature.Effect)
                }
                .startWith(just(GlobalStatsFeature.Effect.StartedLoading))
                .onErrorReturn {
                    GlobalStatsFeature.Effect.ErrorLoading(it)
                }

        }
    }

}