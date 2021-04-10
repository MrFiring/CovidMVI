package ru.mrfiring.covidmvi.domain

import io.reactivex.Completable
import io.reactivex.Single

interface CovidRepository {

    fun fetchGlobalStats(): Single<DomainGlobalStats>
    fun getGlobalStatsFromCache(): Single<DomainGlobalStats>

    fun fetchContinentStats(sortBy: String): Completable
    fun getContinentStatsFromCache(): Single<List<DomainContinentStats>>

}