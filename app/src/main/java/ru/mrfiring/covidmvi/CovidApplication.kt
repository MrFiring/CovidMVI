package ru.mrfiring.covidmvi

import android.app.Application
import android.util.Log
import com.badoo.binder.middleware.base.Middleware
import com.badoo.binder.middleware.config.MiddlewareConfiguration
import com.badoo.binder.middleware.config.Middlewares
import com.badoo.binder.middleware.config.WrappingCondition
import com.badoo.mvicore.consumer.middleware.LoggingMiddleware
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CovidApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        loggingMiddleware()
    }


    private fun loggingMiddleware(){
        Middlewares.configurations.add(
            MiddlewareConfiguration(
                condition = WrappingCondition.Always,
                factories = listOf { consumer ->
                    LoggingMiddleware(
                        consumer,
                        { Log.d(LOG_TAG, it) })
                }
            )
        )
    }


    companion object {
        const val LOG_TAG = "LoggingMiddleware"
    }
}