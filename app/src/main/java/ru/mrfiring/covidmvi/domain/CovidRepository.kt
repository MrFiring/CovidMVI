package ru.mrfiring.covidmvi.domain

import io.reactivex.Single

interface CovidRepository {

    fun getGlobalStats(): Single<DomainGlobalStats>

}