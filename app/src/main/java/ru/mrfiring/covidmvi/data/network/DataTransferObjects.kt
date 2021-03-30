package ru.mrfiring.covidmvi.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GlobalStats(
    val totalConfirmed: Long,
    val totalDeaths: Long,
    val totalRecovered: Long,
    val totalNewCases: Long,
    val totalNewDeaths: Long,
    val totalActiveCases: Long,
    val totalCasesPerMillionPop: Long,
    @Json(name = "created") val creationTime: String
)