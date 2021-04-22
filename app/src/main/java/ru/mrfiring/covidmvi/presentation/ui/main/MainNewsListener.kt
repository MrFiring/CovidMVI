package ru.mrfiring.covidmvi.presentation.ui.main

import androidx.navigation.NavController
import io.reactivex.functions.Consumer
import ru.mrfiring.covidmvi.presentation.features.MainScreenUIFeature

class MainNewsListener(
    val navController: NavController
): Consumer<MainScreenUIFeature.News> {
    override fun accept(news: MainScreenUIFeature.News) = when(news){
        MainScreenUIFeature.News.NavigateToGlobalDetails -> {
            TODO("Implement navigation")
        }
    }
}