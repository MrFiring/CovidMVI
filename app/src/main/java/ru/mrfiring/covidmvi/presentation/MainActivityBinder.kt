package ru.mrfiring.covidmvi.presentation

import android.content.Context
import com.badoo.binder.using
import com.badoo.mvicore.android.AndroidBindings
import dagger.hilt.android.qualifiers.ActivityContext
import ru.mrfiring.covidmvi.presentation.event.UiEventTransformer
import ru.mrfiring.covidmvi.presentation.features.GlobalStatsFeature
import ru.mrfiring.covidmvi.presentation.viewmodel.ViewModelTransformer
import javax.inject.Inject

class MainActivityBinder @Inject constructor(
    @ActivityContext context: Context,
    private val globalStatsFeature: GlobalStatsFeature
) : AndroidBindings<MainActivity>(context as MainActivity) {

    override fun setup(view: MainActivity) {
        binder.bind(view to globalStatsFeature using UiEventTransformer())
        binder.bind(globalStatsFeature to view using ViewModelTransformer())
    }
}