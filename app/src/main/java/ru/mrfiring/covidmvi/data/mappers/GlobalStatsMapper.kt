package ru.mrfiring.covidmvi.data.mappers

import ru.mrfiring.covidmvi.data.database.DatabaseGlobalStats
import ru.mrfiring.covidmvi.data.network.GlobalStats
import ru.mrfiring.covidmvi.domain.DomainGlobalStats

fun GlobalStats.asDatabaseObject(): DatabaseGlobalStats =
    DatabaseGlobalStats(
        lastUpdate,
        cases,
        todayCases,
        deaths,
        todayDeaths,
        recovered,
        todayRecovered,
        active,
        critical,
        casesPerOneMillion,
        deathsPerOneMillion,
        tests,
        testsPerOneMillion,
        population,
        activePerOneMillion,
        recoveredPerOneMillion,
        criticalPerOneMillion,
        affectedCountries
    )

fun DatabaseGlobalStats.asDomainObject(): DomainGlobalStats =
    DomainGlobalStats(
        lastUpdate,
        cases,
        todayCases,
        deaths,
        todayDeaths,
        recovered,
        todayRecovered,
        active,
        critical,
        casesPerOneMillion,
        deathsPerOneMillion,
        tests,
        testsPerOneMillion,
        population,
        activePerOneMillion,
        recoveredPerOneMillion,
        criticalPerOneMillion,
        affectedCountries
    )