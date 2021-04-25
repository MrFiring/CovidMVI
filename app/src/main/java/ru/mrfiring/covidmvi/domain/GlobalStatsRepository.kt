package ru.mrfiring.covidmvi.domain

import io.reactivex.Completable
import io.reactivex.Single

interface GlobalStatsRepository {

    fun fetchGlobalStats(): Completable
    fun getGlobalStatsLatestFromCache(): Single<DomainGlobalStats>

    fun fetchGlobalHistoricalStats(resolution: ResolutionType): Completable
    fun getGlobalHistoricalStatsFromCache(
        resolution: ResolutionType
    ): Single<DomainGlobalHistoricalStats>

}