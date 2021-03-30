package ru.mrfiring.covidmvi.data

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.mrfiring.covidmvi.data.mappers.asDomainObject
import ru.mrfiring.covidmvi.data.network.CovidService
import ru.mrfiring.covidmvi.domain.CovidRepository
import ru.mrfiring.covidmvi.domain.DomainGlobalStats
import javax.inject.Inject

class CovidRepositoryImpl @Inject constructor(
    private val covidService: CovidService
): CovidRepository {
    override fun getGlobalStats(): Single<DomainGlobalStats> {
        return covidService.getGlobalStats()
            .observeOn(Schedulers.io())
            .map {
                it.asDomainObject()
            }
    }
}