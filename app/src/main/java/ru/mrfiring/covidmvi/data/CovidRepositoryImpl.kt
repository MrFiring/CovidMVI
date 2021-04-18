package ru.mrfiring.covidmvi.data

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.mrfiring.covidmvi.data.database.StatsDao
import ru.mrfiring.covidmvi.data.mappers.asDatabaseContinentCountryList
import ru.mrfiring.covidmvi.data.mappers.asDatabaseObject
import ru.mrfiring.covidmvi.data.mappers.asDatabaseObjectList
import ru.mrfiring.covidmvi.data.mappers.asDomainObject
import ru.mrfiring.covidmvi.data.network.CovidService
import ru.mrfiring.covidmvi.domain.*
import javax.inject.Inject

class CovidRepositoryImpl @Inject constructor(
    private val covidService: CovidService,
    private val statsDao: StatsDao
) : CovidRepository {
    override fun fetchGlobalStats(): Completable {
        return covidService.getGlobalStats()
            .subscribeOn(Schedulers.io())
            .map {
                it.asDatabaseObject()
            }
            .flatMapCompletable {
                statsDao.insertGlobalStats(it)
            }
    }

    override fun getGlobalStatsLatestFromCache(): Single<DomainGlobalStats> {
        return statsDao.getGlobalStatsList()
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

    override fun fetchContinentStats(sortBy: String): Completable {
        return covidService.getStatsByContinents(sortBy)
            .subscribeOn(Schedulers.io())
            .flatMapCompletable { networkContinentList ->
                Completable.mergeArray(
                    statsDao.insertAllContinentStats(
                        networkContinentList.map {
                            it.asDatabaseObject()
                        }
                    ),

                    Observable.fromIterable(networkContinentList)
                        .flatMapCompletable {
                            statsDao.insertAllContinentCountry(
                                it.countriesList.asDatabaseContinentCountryList(
                                    continent = it.continentName
                                )
                            )
                        }

                    )
            }
    }

    override fun getContinentStatsFromCache(): Single<List<DomainContinentStats>> {
        return statsDao.getContinentStatsList()
            .subscribeOn(Schedulers.io())
            .flatMapObservable {
                Observable.fromIterable(it)
            }
            .flatMapSingle { dbStats ->
                statsDao.getContinentCountryList(dbStats.continentName)
                    .map { dbCountryList ->
                        dbStats.asDomainObject(
                            dbCountryList.map { it.countryName }
                        )
                    }
            }
            .toList()
    }


    override fun fetchGlobalHistoricalStats(resolution: ResolutionType): Completable {
        return covidService.getGlobalHistoricalStats(resolution.str)
            .map {
                it.asDatabaseObjectList(resolution.str)
            }
            .flatMapCompletable {
                statsDao.insertAllGlobalHistoricalStats(it)
            }
    }

    override fun getGlobalHistoricalStatsFromCache(resolution: ResolutionType): Single<DomainGlobalHistoricalStats> {
        return statsDao.getGlobalHistoricalStatsByResolution(resolution.str)
            .map {
                it.asDomainObject()
            }
    }
}