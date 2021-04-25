package ru.mrfiring.covidmvi.domain

import io.reactivex.Completable
import io.reactivex.Single

interface ContinentStatsRepository {
    fun fetchContinentStats(sortBy: String): Completable
    fun getContinentStatsFromCache(): Single<List<DomainContinentStats>>
}