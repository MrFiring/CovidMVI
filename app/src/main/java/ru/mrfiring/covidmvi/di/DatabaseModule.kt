package ru.mrfiring.covidmvi.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.mrfiring.covidmvi.data.database.CovidDatabase
import ru.mrfiring.covidmvi.data.database.StatsDao
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
    fun provideStatsDao(covidDatabase: CovidDatabase): StatsDao = covidDatabase.statsDao

}