package ru.mrfiring.covidmvi.presentation.ui.main

import androidx.navigation.NavController
import io.reactivex.functions.Consumer
import ru.mrfiring.covidmvi.presentation.features.MainScreenUIFeature
import javax.inject.Inject

class MainNewsListener @Inject constructor(
    private val navController: NavController
): Consumer<MainScreenUIFeature.News> {
    override fun accept(news: MainScreenUIFeature.News) {
        when (news) {
            MainScreenUIFeature.News.NavigateToGlobalDetails -> {
                TODO("Call navigate")
            }
        }
    }
}