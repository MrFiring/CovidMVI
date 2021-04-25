package ru.mrfiring.covidmvi.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.mrfiring.covidmvi.data.ContinentStatsRepositoryImpl
import ru.mrfiring.covidmvi.data.GlobalStatsRepositoryImpl
import ru.mrfiring.covidmvi.domain.ContinentStatsRepository
import ru.mrfiring.covidmvi.domain.GlobalStatsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideGlobalStatsRepository(
        globalStatsRepositoryImpl: GlobalStatsRepositoryImpl
    ): GlobalStatsRepository = globalStatsRepositoryImpl

    @Singleton
    @Provides
    fun provideContinentStatsRepository(
        continentStatsRepositoryImpl: ContinentStatsRepositoryImpl
    ): ContinentStatsRepository = continentStatsRepositoryImpl

}