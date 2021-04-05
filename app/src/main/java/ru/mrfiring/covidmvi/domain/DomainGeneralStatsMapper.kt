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