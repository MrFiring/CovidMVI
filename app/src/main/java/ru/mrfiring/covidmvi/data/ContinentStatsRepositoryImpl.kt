package ru.mrfiring.covidmvi.data

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.mrfiring.covidmvi.data.database.ContinentStatsDao
import ru.mrfiring.covidmvi.data.mappers.asDatabaseContinentCountryList
import ru.mrfiring.covidmvi.data.mappers.asDatabaseObject
import ru.mrfiring.covidmvi.data.mappers.asDomainObject
import ru.mrfiring.covidmvi.data.network.CovidService
import ru.mrfiring.covidmvi.domain.ContinentStatsRepository
import ru.mrfiring.covidmvi.domain.DomainContinentStats
import javax.inject.Inject

class ContinentStatsRepositoryImpl @Inject constructor(
    private val covidService: CovidService,
    private val continentStatsDao: ContinentStatsDao
): ContinentStatsRepository {

    override fun fetchContinentStats(sortBy: String): Completable {
        return covidService.getStatsByContinents(sortBy)
            .subscribeOn(Schedulers.io())
            .flatMapCompletable { networkContinentList ->
                Completable.mergeArray(
                    continentStatsDao.insertAllContinentStats(
                        networkContinentList.map {
                            it.asDatabaseObject()
                        }
                    ),

                    Observable.fromIterable(networkContinentList)
                        .flatMapCompletable {
                            continentStatsDao.insertAllContinentCountry(
                                it.countriesList.asDatabaseContinentCountryList(
                                    continent = it.continentName
                                )
                            )
                        }

                )
            }
    }

    override fun getContinentStatsFromCache(): Single<List<DomainContinentStats>> {
        return continentStatsDao.getContinentStatsList()
            .subscribeOn(Schedulers.io())
            .flatMapObservable {
                Observable.fromIterable(it)
            }
            .flatMapSingle { dbStats ->
                continentStatsDao.getContinentCountryList(dbStats.continentName)
                    .map { dbCountryList ->
                        dbStats.asDomainObject(
                            dbCountryList.map { it.countryName }
                        )
                    }
            }
            .toList()
    }

}