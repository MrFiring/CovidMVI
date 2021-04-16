package ru.mrfiring.covidmvi.presentation.features

import com.badoo.mvicore.element.Bootstrapper
import com.badoo.mvicore.element.PostProcessor
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.BaseFeature
import io.reactivex.Observable
import io.reactivex.Observable.just
import ru.mrfiring.covidmvi.domain.DomainGlobalStats
import ru.mrfiring.covidmvi.domain.GlobalStatsActorImpl
import javax.inject.Inject

class GlobalStatsFeature @Inject constructor(
    actor: GlobalStatsActorImpl
) :
    BaseFeature<
            GlobalStatsFeature.Wish,
            GlobalStatsFeature.Wish,
            GlobalStatsFeature.Effect,
            GlobalStatsFeature.State,
            Nothing>(
        initialState = State(),
        actor = actor,
        bootstrapper = BootstrapperImpl(),
        postProcessor = PostProcessorImpl(),
        wishToAction = {it},
        reducer = ReducerImpl()
    ) {


    data class State(
        val isLoading: Boolean = false,
        val globalStats: DomainGlobalStats? = null
    )

    sealed class Wish {
        object LoadNewGlobalStats : Wish()
        object LoadGlobalStatsFromCache : Wish()
    }

    sealed class Effect {
        object StartedLoading : Effect()
        data class LoadedGlobalStats(val globalStats: DomainGlobalStats) : Effect()
        object LoadedNewGlobalStats : Effect()
        data class ErrorLoading(val throwable: Throwable) : Effect()
    }

    private class BootstrapperImpl : Bootstrapper<Wish> {
        override fun invoke(): Observable<Wish> = just(Wish.LoadGlobalStatsFromCache)
    }

    private class PostProcessorImpl : PostProcessor<Wish, Effect, State> {
        override fun invoke(action: Wish, effect: Effect, state: State): Wish? = when (effect) {
            is Effect.LoadedGlobalStats -> {
                if (state.globalStats?.lastUpdate == 0L) {
                    Wish.LoadNewGlobalStats
                } else null
            }
            is Effect.LoadedNewGlobalStats ->{
                Wish.LoadGlobalStatsFromCache
            }

            else -> null
        }
    }

    private class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State = when (effect) {
            Effect.StartedLoading -> state.copy(
                isLoading = true
            )
            Effect.LoadedNewGlobalStats -> state.copy(
                isLoading = false
            )
            is Effect.LoadedGlobalStats -> state.copy(
                isLoading = false,
                globalStats = effect.globalStats
            )
            is Effect.ErrorLoading -> state.copy(
                isLoading = false
            )
        }
    }

}