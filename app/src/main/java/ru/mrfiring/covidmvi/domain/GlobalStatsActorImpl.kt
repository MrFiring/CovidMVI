package ru.mrfiring.covidmvi.domain

import android.util.Log
import com.badoo.mvicore.element.Actor
import io.reactivex.Observable
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
                .flatMapObservable{
                    Observable.just(GlobalStatsFeature.Effect.LoadedGlobalStats(it) as GlobalStatsFeature.Effect)
                }
                .startWith(Observable.just(GlobalStatsFeature.Effect.StartedLoading))
                .onErrorReturn {
                    GlobalStatsFeature.Effect.ErrorLoading(it)
                }
        }
    }

}