package ru.mrfiring.covidmvi.presentation.event

import ru.mrfiring.covidmvi.presentation.features.MainScreenUIFeature

class UiEventTransformer: (UiEvent) -> MainScreenUIFeature.Wish? {
    override fun invoke(event: UiEvent): MainScreenUIFeature.Wish? = when(event){
        is UiEvent.ButtonClicked -> MainScreenUIFeature.Wish.NavigateToGlobalDetails
    }
}