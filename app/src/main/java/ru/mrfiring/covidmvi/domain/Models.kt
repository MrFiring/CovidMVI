package ru.mrfiring.covidmvi.domain

data class DomainGlobalStats(
    val totalConfirmed: Long,
    val totalDeaths: Long,
    val totalRecovered: Long,
    val totalNewCases: Long,
    val totalNewDeaths: Long,
    val totalActiveCases: Long,
    val totalCasesPerMillionPop: Long,
    val creationTime: String
)