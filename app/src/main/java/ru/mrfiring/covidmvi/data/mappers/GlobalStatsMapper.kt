package ru.mrfiring.covidmvi.data.mappers

import ru.mrfiring.covidmvi.data.network.GlobalStats
import ru.mrfiring.covidmvi.domain.DomainGlobalStats

fun GlobalStats.asDomainObject(): DomainGlobalStats = DomainGlobalStats(
    totalConfirmed,
    totalDeaths,
    totalRecovered,
    totalNewCases,
    totalNewDeaths,
    totalActiveCases,
    totalCasesPerMillionPop,
    creationTime
)