package ru.mrfiring.covidmvi.presentation.ui.main

import androidx.lifecycle.LifecycleOwner
import com.badoo.binder.using
import com.badoo.mvicore.android.AndroidBindings
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.mrfiring.covidmvi.presentation.event.UiEventTransformer
import ru.mrfiring.covidmvi.presentation.features.ContinentStatsFeature
import ru.mrfiring.covidmvi.presentation.features.GlobalStatsFeature
import ru.mrfiring.covidmvi.presentation.features.MainScreenUIFeature
import ru.mrfiring.covidmvi.presentation.viewmodel.MainViewModelPairedTransformer
import ru.mrfiring.covidmvi.utils.combineLatest

class MainFragmentBinder @AssistedInject constructor(
    @Assisted private val lifecycle: LifecycleOwner,
    private val continentStatsFeature: ContinentStatsFeature,
    private val globalStatsFeature: GlobalStatsFeature,
    private val mainScreenUIFeature: MainScreenUIFeature,
    private val mainNewsListener: MainNewsListener
) : AndroidBindings<MainFragment>(lifecycle) {
    override fun setup(view: MainFragment) {
        binder.bind(
            combineLatest(
                continentStatsFeature,
                globalStatsFeature
            ) to view using MainViewModelPairedTransformer()
        )

        binder.bind(view to mainScreenUIFeature using UiEventTransformer())
        binder.bind(mainScreenUIFeature.news to mainNewsListener)
    }

    @AssistedFactory
    interface MainFragmentBinderFactory {
        fun create(lifecycle: LifecycleOwner): MainFragmentBinder
    }
}