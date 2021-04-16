package ru.mrfiring.covidmvi.presentation.viewmodel

import ru.mrfiring.covidmvi.presentation.features.ContinentStatsFeature
import ru.mrfiring.covidmvi.presentation.features.GlobalStatsFeature

class ViewModelPairedTransformer: (
    Pair<ContinentStatsFeature.State, GlobalStatsFeature.State>
) -> ViewModel {
    override fun invoke(
        pair: Pair<ContinentStatsFeature.State, GlobalStatsFeature.State>
    ): ViewModel {
        val (continentState, globalState) = pair

        return ViewModel(
            globalState.globalStats,
            continentState.continentStats,
            continentState.isLoading || globalState.isLoading
        )

    }
}