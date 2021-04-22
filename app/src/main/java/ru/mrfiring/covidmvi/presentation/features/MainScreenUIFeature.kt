package ru.mrfiring.covidmvi.presentation.features

import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ReducerFeature

class MainScreenUIFeature :
    ReducerFeature<
            MainScreenUIFeature.Wish,
            MainScreenUIFeature.State,
            MainScreenUIFeature.News>(
        initialState = State(),
        reducer = ReducerImpl(),
        newsPublisher = NewsPublisherImpl()
    ) {

    sealed class Wish {
        object NavigateToGlobalDetails: Wish()
    }

    class State

    sealed class News {
        object NavigateToGlobalDetails: News()
    }

    private class NewsPublisherImpl: SimpleNewsPublisher<Wish, State, News>(){
        override fun invoke(wish: Wish, state: State): News = when(wish){
            Wish.NavigateToGlobalDetails -> News.NavigateToGlobalDetails
        }
    }


    private class ReducerImpl: Reducer<State, Wish>{
        override fun invoke(state: State, wish: Wish): State {
            return State()
        }
    }

}