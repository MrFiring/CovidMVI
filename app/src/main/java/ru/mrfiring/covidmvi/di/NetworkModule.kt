package ru.mrfiring.covidmvi.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.mrfiring.covidmvi.data.network.BASE_URL
import ru.mrfiring.covidmvi.data.network.CovidService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(
            Schedulers.io()
        ))
        .build()

    @Singleton
    @Provides
    fun provideCovidService(retrofit: Retrofit): CovidService = retrofit.create(
        CovidService::class.java
    )
}