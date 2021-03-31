package ru.mrfiring.covidmvi.presentation.viewmodel

import ru.mrfiring.covidmvi.domain.DomainGlobalStats

data class ViewModel(
    val globalStats: DomainGlobalStats,
    val isLoading: Boolean
)