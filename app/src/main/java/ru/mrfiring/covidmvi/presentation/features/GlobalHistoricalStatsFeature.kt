package ru.mrfiring.covidmvi.presentation.features

import com.badoo.mvicore.element.Bootstrapper
import com.badoo.mvicore.element.PostProcessor
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.BaseFeature
import io.reactivex.Observable
import io.reactivex.Observable.just
import ru.mrfiring.covidmvi.domain.DomainGlobalHistoricalStats
import ru.mrfiring.covidmvi.domain.GlobalHistoricalStatsActorImpl
import ru.mrfiring.covidmvi.domain.ResolutionType
import javax.inject.Inject

class GlobalHistoricalStatsFeature @Inject constructor(
    actor: GlobalHistoricalStatsActorImpl
) : BaseFeature<
        GlobalHistoricalStatsFeature.Wish,
        GlobalHistoricalStatsFeature.Wish,
        GlobalHistoricalStatsFeature.Effect,
        GlobalHistoricalStatsFeature.State,
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
        val resolution: ResolutionType = ResolutionType.TWO_WEEKS,
        val globalHistoricalStats: DomainGlobalHistoricalStats? = null
    )

    sealed class Wish {
        object LoadNewHistoricalStats : Wish()
        object LoadHistoricalStatsFromCache : Wish()
        data class SetResolutionType(val resolution: ResolutionType) : Wish()
    }

    sealed class Effect {
        object StartedLoading : Effect()
        data class LoadedHistoricalStats(val stats: DomainGlobalHistoricalStats) : Effect()
        object LoadedNewHistoricalStats : Effect()
        data class ErrorLoading(val throwable: Throwable) : Effect()

        data class SetResolutionType(val resolution: ResolutionType) : Effect()
    }

    private class BootstrapperImpl : Bootstrapper<Wish> {
        override fun invoke(): Observable<Wish> = just(Wish.LoadHistoricalStatsFromCache)
    }

    private class PostProcessorImpl : PostProcessor<Wish, Effect, State> {
        override fun invoke(action: Wish, effect: Effect, state: State): Wish? = when (effect) {
            is Effect.LoadedHistoricalStats -> {
                if (state.globalHistoricalStats?.cases?.isEmpty() as Boolean) {
                    Wish.LoadNewHistoricalStats
                } else null
            }
            is Effect.LoadedNewHistoricalStats -> {
                Wish.LoadHistoricalStatsFromCache
            }

            else -> null
        }
    }

    private class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State = when (effect) {
            Effect.StartedLoading -> state.copy(
                isLoading = true
            )
            Effect.LoadedNewHistoricalStats -> state.copy(
                isLoading = false
            )
            is Effect.LoadedHistoricalStats -> state.copy(
                isLoading = false,
                globalHistoricalStats = effect.stats
            )
            is Effect.ErrorLoading -> state.copy(
                isLoading = false
            )
            is Effect.SetResolutionType -> state.copy(
                resolution = effect.resolution
            )
        }
    }

}