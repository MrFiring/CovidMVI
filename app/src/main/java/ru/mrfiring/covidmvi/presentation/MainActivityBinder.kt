package ru.mrfiring.covidmvi.presentation

import com.badoo.binder.using
import com.badoo.mvicore.android.AndroidBindings
import ru.mrfiring.covidmvi.presentation.event.UiEventTransformer
import ru.mrfiring.covidmvi.presentation.features.GlobalStatsFeature
import ru.mrfiring.covidmvi.presentation.viewmodel.ViewModelTransformer

class MainActivityBinder(
    view: MainActivity,
    private val globalStatsFeature: GlobalStatsFeature
) : AndroidBindings<MainActivity>(view) {

    override fun setup(view: MainActivity) {
        binder.bind(view to globalStatsFeature using UiEventTransformer())
        binder.bind(globalStatsFeature to view using ViewModelTransformer())
    }
}