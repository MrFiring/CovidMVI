package ru.mrfiring.covidmvi.presentation.viewmodel

import ru.mrfiring.covidmvi.domain.DomainContinentStats
import ru.mrfiring.covidmvi.domain.DomainGlobalHistoricalStats
import ru.mrfiring.covidmvi.domain.DomainGlobalStats

sealed class ViewModel{
    abstract val isLoading: Boolean

    data class Main(
        val globalStats: DomainGlobalStats?,
        val continentStatsList: List<DomainContinentStats>?,
        override val isLoading: Boolean
    ): ViewModel()

    data class GlobalDetail(
        val globalStats: DomainGlobalStats?,
        val historicalStats: DomainGlobalHistoricalStats?,
        override val isLoading: Boolean
    ): ViewModel()

}
