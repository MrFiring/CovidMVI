package ru.mrfiring.covidmvi.domain

import io.reactivex.Single

interface CovidRepository {

    fun fetchGlobalStats(): Single<DomainGlobalStats>
    fun getGlobalStatsFromCache(): Single<DomainGlobalStats>

    fun getContinentStatsFromCache(): Single<List<DomainContinentStats>>

}