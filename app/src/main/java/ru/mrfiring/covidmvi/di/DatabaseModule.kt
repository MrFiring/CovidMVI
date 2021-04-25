package ru.mrfiring.covidmvi.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.mrfiring.covidmvi.data.database.ContinentStatsDao
import ru.mrfiring.covidmvi.data.database.CountryStatsDao
import ru.mrfiring.covidmvi.data.database.CovidDatabase
import ru.mrfiring.covidmvi.data.database.GlobalStatsDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun provideCovidDatabase(@ApplicationContext context: Context): CovidDatabase =
        Room.databaseBuilder(context, CovidDatabase::class.java, "covid_db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideGlobalStatsDao(covidDatabase: CovidDatabase): GlobalStatsDao = covidDatabase.globalStatsDao

    @Provides
    @Singleton
    fun provideContinentStatsDao(covidDatabase: CovidDatabase): ContinentStatsDao = covidDatabase.continentStatsDao

    @Provides
    @Singleton
    fun provideCountryStatsDao(covidDatabase: CovidDatabase): CountryStatsDao = covidDatabase.countryStatsDao

}