package ru.mrfiring.covidmvi.presentation.features

import com.badoo.mvicore.feature.ActorReducerFeature
import com.badoo.mvicore.feature.Feature

class MainScreenUIFeature :
    ActorReducerFeature<
            MainScreenUIFeature.Wish,
            MainScreenUIFeature.Effect,
            Nothing,
            MainScreenUIFeature.News>(

    ) {

    sealed class Wish {

    }

    sealed class Effect {

    }

    sealed class News {

    }


}