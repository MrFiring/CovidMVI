package ru.mrfiring.covidmvi.data

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.mrfiring.covidmvi.data.database.GlobalStatsDao
import ru.mrfiring.covidmvi.data.mappers.asDatabaseContinentCountryList
import ru.mrfiring.covidmvi.data.mappers.asDatabaseObject
import ru.mrfiring.covidmvi.data.mappers.asDatabaseObjectList
import ru.mrfiring.covidmvi.data.mappers.asDomainObject
import ru.mrfiring.covidmvi.data.network.CovidService
import ru.mrfiring.covidmvi.domain.*
import javax.inject.Inject

class GlobalStatsRepositoryImpl @Inject constructor(
    private val covidService: CovidService,
    private val globalStatsDao: GlobalStatsDao
) : GlobalStatsRepository {
    override fun fetchGlobalStats(): Completable {
        return covidService.getGlobalStats()
            .subscribeOn(Schedulers.io())
            .map {
                it.asDatabaseObject()
            }
            .flatMapCompletable {
                globalStatsDao.insertGlobalStats(it)
            }
    }

    override fun getGlobalStatsLatestFromCache(): Single<DomainGlobalStats> {
        return globalStatsDao.getGlobalStatsList()
            .subscribeOn(Schedulers.io())
            .map { list ->
                 list.map {
                     it.asDomainObject()
                 }
            }
            .flatMap {
                Single.just(it.firstOrNull() ?: DomainGlobalStats())
            }

    }

    override fun fetchGlobalHistoricalStats(resolution: ResolutionType): Completable {
        return covidService.getGlobalHistoricalStats(resolution.str)
            .subscribeOn(Schedulers.io())
            .map {
                it.asDatabaseObjectList(resolution.str)
            }
            .flatMapCompletable {
                globalStatsDao.insertAllGlobalHistoricalStats(it)
            }
    }

    override fun getGlobalHistoricalStatsFromCache(resolution: ResolutionType): Single<DomainGlobalHistoricalStats> {
        return globalStatsDao.getGlobalHistoricalStatsByResolution(resolution.str)
            .subscribeOn(Schedulers.io())
            .map {
                it.asDomainObject()
            }
    }
}