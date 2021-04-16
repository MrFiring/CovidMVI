package ru.mrfiring.covidmvi.data.mappers

import ru.mrfiring.covidmvi.data.database.DatabaseContinentStats
import ru.mrfiring.covidmvi.data.network.ContinentStats
import ru.mrfiring.covidmvi.domain.DomainContinentStats

fun ContinentStats.asDatabaseObject(): DatabaseContinentStats =
    DatabaseContinentStats(
        continentName,
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
        lastUpdate
    )

fun DatabaseContinentStats.asDomainObject(countries: List<String>): DomainContinentStats =
    DomainContinentStats(
        continentName,
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
        lastUpdate,
        countries
    )