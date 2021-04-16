package ru.mrfiring.covidmvi.domain

fun DomainContinentStats.asDomainGeneralStats(): DomainGeneralStats =
    DomainGeneralStats(
        continentName,
        cases,
        todayCases,
        recovered,
        todayRecovered,
        deaths,
        todayDeaths
    )

fun DomainGlobalStats.asDomainGeneralStats(): DomainGeneralStats =
    DomainGeneralStats(
        name = "global",
        cases,
        todayCases,
        recovered,
        todayRecovered,
        deaths,
        todayDeaths
    )