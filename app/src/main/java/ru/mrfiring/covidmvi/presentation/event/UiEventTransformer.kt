package ru.mrfiring.covidmvi.presentation.event

import ru.mrfiring.covidmvi.presentation.features.GlobalStatsFeature

class UiEventTransformer: (UiEvent) -> GlobalStatsFeature.Wish? {
    override fun invoke(event: UiEvent): GlobalStatsFeature.Wish? = when(event){
        is UiEvent.ButtonClicked -> GlobalStatsFeature.Wish.LoadNewGlobalStats
    }
}