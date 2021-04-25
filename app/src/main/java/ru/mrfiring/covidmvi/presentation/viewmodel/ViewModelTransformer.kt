package ru.mrfiring.covidmvi.presentation.viewmodel

import ru.mrfiring.covidmvi.presentation.features.ContinentStatsFeature
import ru.mrfiring.covidmvi.presentation.features.GlobalHistoricalStatsFeature
import ru.mrfiring.covidmvi.presentation.features.GlobalStatsFeature

class MainViewModelPairedTransformer : (
    Pair<ContinentStatsFeature.State, GlobalStatsFeature.State>
) -> ViewModel.Main {
    override fun invoke(
        pair: Pair<ContinentStatsFeature.State, GlobalStatsFeature.State>
    ): ViewModel.Main {
        val (continentState, globalState) = pair

        return ViewModel.Main(
            globalState.globalStats,
            continentState.continentStats,
            continentState.isLoading || globalState.isLoading
        )

    }
}

class GlobalDetailViewModelPairedTransformer : (
    Pair<GlobalStatsFeature.State, GlobalHistoricalStatsFeature.State>
) -> ViewModel.GlobalDetail {
    override fun invoke(
        pair: Pair<GlobalStatsFeature.State, GlobalHistoricalStatsFeature.State>
    ): ViewModel.GlobalDetail {
        val (globalState, historicalState) = pair

        return ViewModel.GlobalDetail(
            globalStats = globalState.globalStats,
            historicalStats = historicalState.globalHistoricalStats,
            isLoading = globalState.isLoading || historicalState.isLoading
        )
    }
}