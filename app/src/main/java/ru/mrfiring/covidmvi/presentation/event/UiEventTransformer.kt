package ru.mrfiring.covidmvi.presentation.event

import ru.mrfiring.covidmvi.presentation.features.GlobalHistoricalStatsFeature
import ru.mrfiring.covidmvi.presentation.features.MainScreenUIFeature

class UiEventTransformer: (UiEvent) -> MainScreenUIFeature.Wish? {
    override fun invoke(event: UiEvent): MainScreenUIFeature.Wish? = when(event){
        is UiEvent.ButtonClicked -> MainScreenUIFeature.Wish.NavigateToGlobalDetails
        else -> null
    }
}

class UiEventToGlobalHistoricalWishTransformer: (UiEvent) -> GlobalHistoricalStatsFeature.Wish? {
    override fun invoke(event: UiEvent): GlobalHistoricalStatsFeature.Wish? = when(event) {
        is UiEvent.ChartFilterButtonClicked -> GlobalHistoricalStatsFeature.Wish.SetResolutionType(event.resolution)
        else -> null
    }
}