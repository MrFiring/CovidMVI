package ru.mrfiring.covidmvi.presentation.viewmodel

import ru.mrfiring.covidmvi.domain.DomainGlobalStats
import ru.mrfiring.covidmvi.presentation.features.GlobalStatsFeature

class ViewModelTransformer: (GlobalStatsFeature.State) -> ViewModel {
    override fun invoke(state: GlobalStatsFeature.State): ViewModel = ViewModel(
        globalStats = state.globalStats ?: DomainGlobalStats(
            0, 0, 0 ,0 ,
        0, 0, 0, ""),
        isLoading = state.isLoading
    )
}