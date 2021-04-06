package ru.mrfiring.covidmvi.data

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.mrfiring.covidmvi.data.database.StatsDao
import ru.mrfiring.covidmvi.data.mappers.asDatabaseObject
import ru.mrfiring.covidmvi.data.mappers.asDomainObject
import ru.mrfiring.covidmvi.data.network.CovidService
import ru.mrfiring.covidmvi.domain.CovidRepository
import ru.mrfiring.covidmvi.domain.DomainGlobalStats
import javax.inject.Inject

class CovidRepositoryImpl @Inject constructor(
    private val covidService: CovidService,
    private val statsDao: StatsDao
): CovidRepository {
    override fun fetchGlobalStats(): Single<DomainGlobalStats> {
        return covidService.getGlobalStats()
            .subscribeOn(Schedulers.io())
            .map {
                it.asDatabaseObject()
            }
            .flatMapCompletable {
                statsDao.insertGlobalStats(it)
            }
            .andThen(statsDao.getGlobalStats().map {
                it.asDomainObject()
            })

    }

    override fun getGlobalStatsFromCache(): Single<DomainGlobalStats> {
        return statsDao.getGlobalStats()
            .subscribeOn(Schedulers.io())
            .map {
                it.asDomainObject()
            }
    }
}