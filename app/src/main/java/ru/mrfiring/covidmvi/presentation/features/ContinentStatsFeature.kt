package ru.mrfiring.covidmvi.presentation.features

import com.badoo.mvicore.element.Bootstrapper
import com.badoo.mvicore.element.PostProcessor
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import io.reactivex.Observable
import io.reactivex.Observable.just
import ru.mrfiring.covidmvi.domain.ContinentStatsActorImpl
import ru.mrfiring.covidmvi.domain.DomainGeneralStats
import javax.inject.Inject

class ContinentStatsFeature @Inject constructor(
    actor: ContinentStatsActorImpl
) : ActorReducerFeature<
        ContinentStatsFeature.Wish,
        ContinentStatsFeature.Effect,
        ContinentStatsFeature.State,
        Nothing>(
    initialState = State(),
    bootstrapper = BootstrapperImpl(),
    actor = actor,
    reducer = ReducerImpl(),
) {

    data class State(
        val isLoading: Boolean = false,
        val continentStats: List<DomainGeneralStats>? = null
    )

    sealed class Wish {
        object LoadNewContinentStats : Wish()
        object LoadCacheContinentStats : Wish()
    }

    sealed class Effect {
        object StartedLoading : Effect()
        data class LoadedNewGlobalStats(val continentStats: List<DomainGeneralStats>) : Effect()
        data class LoadedCacheGlobalStats(val continentStats: List<DomainGeneralStats>) : Effect()
        data class ErrorLoading(val throwable: Throwable) : Effect()
    }

    private class BootstrapperImpl : Bootstrapper<Wish> {
        override fun invoke(): Observable<Wish> = just(Wish.LoadCacheContinentStats)
    }

    private class PostProcessorImpl : PostProcessor<Wish, Effect, State> {
        override fun invoke(action: Wish, effect: Effect, state: State): Wish? = when (effect) {
            is Effect.LoadedCacheGlobalStats -> {
                if (state.continentStats.isNullOrEmpty()) {
                    Wish.LoadNewContinentStats
                } else null
            }

            else -> null
        }
    }

    private class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State = when (effect) {
            Effect.StartedLoading -> state.copy(
                isLoading = true
            )
            is Effect.LoadedNewGlobalStats -> state.copy(
                isLoading = false,
                continentStats = effect.continentStats
            )
            is Effect.LoadedCacheGlobalStats -> state.copy(
                isLoading = false,
                continentStats = effect.continentStats
            )
            is Effect.ErrorLoading -> state.copy(
                isLoading = false
            )
        }
    }

}