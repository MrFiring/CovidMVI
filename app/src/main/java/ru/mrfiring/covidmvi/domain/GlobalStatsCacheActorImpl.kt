package ru.mrfiring.covidmvi.domain

import com.badoo.mvicore.element.Actor
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.mrfiring.covidmvi.presentation.features.GlobalStatsCacheFeature
import javax.inject.Inject

class GlobalStatsCacheActorImpl @Inject constructor(
    private val repository: CovidRepository
):Actor<GlobalStatsCacheFeature.State, GlobalStatsCacheFeature.Wish, GlobalStatsCacheFeature.Effect> {
    override fun invoke(
        state: GlobalStatsCacheFeature.State,
        wish: GlobalStatsCacheFeature.Wish
    ): Observable<GlobalStatsCacheFeature.Effect> = when(wish){
        is GlobalStatsCacheFeature.Wish.LoadGlobalStatsFromCache -> repository.getGlobalStatsFromCache()
            .observeOn(AndroidSchedulers.mainThread())
            .flatMapObservable {
                Observable.just(GlobalStatsCacheFeature.Effect.LoadedGlobalStats(it) as GlobalStatsCacheFeature.Effect)
            }
            .startWith(
                Observable.just(GlobalStatsCacheFeature.Effect.StartedLoading)
            )
            .onErrorReturn {
                GlobalStatsCacheFeature.Effect.ErrorLoading(it)
            }
    }
}