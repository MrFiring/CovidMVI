package ru.mrfiring.covidmvi.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.mrfiring.covidmvi.data.CovidRepositoryImpl
import ru.mrfiring.covidmvi.domain.CovidRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideCovidRepository(covidRepositoryImpl: CovidRepositoryImpl): CovidRepository {
        return covidRepositoryImpl
    }

}