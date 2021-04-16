package ru.mrfiring.covidmvi.domain

enum class SortType(val str: String){
    CASES("cases"),
    TODAY_CASES("todayCases"),
    DEATHS("deaths"),
    TODAY_DEATHS("todayDeaths"),
    RECOVERED("recovered"),
    ACTIVE("active"),
    CRITICAL("critical"),
    CASES_PER_ONE_MILLION("casesPerOneMillion"),
    DEATHS_PER_ONE_MILLION("deathsPerOneMillion")
}