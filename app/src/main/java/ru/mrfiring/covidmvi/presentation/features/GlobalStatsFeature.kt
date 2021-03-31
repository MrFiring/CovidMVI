package ru.mrfiring.covidmvi.presentation.features

import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import ru.mrfiring.covidmvi.domain.DomainGlobalStats
import ru.mrfiring.covidmvi.domain.GlobalStatsActorImpl
import javax.inject.Inject

class GlobalStatsFeature @Inject constructor(
    private val actor: GlobalStatsActorImpl
):
    ActorReducerFeature<GlobalStatsFeature.Wish, GlobalStatsFeature.Effect, GlobalStatsFeature.State, Nothing>(
        initialState = State(),
        actor = actor,
        reducer = ReducerImpl()
    ) {


    data class State(
        val isLoading: Boolean = false,
        val globalStats: DomainGlobalStats? = null
    )

    sealed class Wish{
        object LoadNewGlobalStats: Wish()
    }

    sealed class Effect{
        object StartedLoading: Effect()
        data class LoadedGlobalStats(val globalStats: DomainGlobalStats): Effect()
        data class ErrorLoading(val throwable: Throwable): Effect()
    }

    class ReducerImpl: Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State = when(effect){
            Effect.StartedLoading -> state.copy(
                isLoading = true
            )
            is Effect.LoadedGlobalStats -> state.copy(
                isLoading = false,
                globalStats =  effect.globalStats
            )
            is Effect.ErrorLoading -> state.copy(
                isLoading = false
            )
        }
    }

}