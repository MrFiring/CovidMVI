package ru.mrfiring.covidmvi.presentation.event

import ru.mrfiring.covidmvi.domain.ResolutionType

sealed class UiEvent {
    object ButtonClicked: UiEvent()
    
    data class ChartFilterButtonClicked(
        val resolution: ResolutionType
    ): UiEvent()
}
