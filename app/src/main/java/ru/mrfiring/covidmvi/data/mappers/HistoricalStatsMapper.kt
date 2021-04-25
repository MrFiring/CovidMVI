package ru.mrfiring.covidmvi.data.mappers

import ru.mrfiring.covidmvi.data.database.DatabaseGlobalHistoricalStats
import ru.mrfiring.covidmvi.data.network.HistoricalData
import ru.mrfiring.covidmvi.domain.DomainGlobalHistoricalStats

fun HistoricalData.asDatabaseObjectList(resolution: String): List<DatabaseGlobalHistoricalStats>{
    val result = mutableListOf<DatabaseGlobalHistoricalStats>()

    cases.forEach { (date, caseCount) ->
        result.add(
            DatabaseGlobalHistoricalStats(
                resolution = resolution,
                date = date,
                cases = caseCount,
                deaths = deaths[date] ?: 0,
                recovered = recovered[date] ?: 0
            )
        )
    }

    return result
}

fun List<DatabaseGlobalHistoricalStats>.asDomainObject(): DomainGlobalHistoricalStats{
    val cases = mutableMapOf<String, Long>()
    val recovered = mutableMapOf<String, Long>()
    val deaths = mutableMapOf<String, Long>()

    forEach { dbObj ->
        cases[dbObj.date] = dbObj.cases
        recovered[dbObj.date] = dbObj.recovered
        deaths[dbObj.date] = dbObj.deaths
    }

    return DomainGlobalHistoricalStats(
        cases,
        deaths,
        recovered
    )
}