package ru.mrfiring.covidmvi.presentation.di

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import ru.mrfiring.covidmvi.R

@Module
@InstallIn(ActivityComponent::class)
object MainActivityModule {

    @Provides
    fun provideNavController(activity: Activity): NavController =
        activity.findNavController(R.id.nav_host_fragment)

}