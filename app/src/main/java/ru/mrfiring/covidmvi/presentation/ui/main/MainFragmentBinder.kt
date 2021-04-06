package ru.mrfiring.covidmvi.presentation.ui.main

import androidx.lifecycle.LifecycleOwner
import com.badoo.binder.using
import com.badoo.mvicore.android.AndroidBindings
import ru.mrfiring.covidmvi.presentation.features.GlobalStatsCacheFeature
import ru.mrfiring.covidmvi.presentation.features.GlobalStatsFeature
import ru.mrfiring.covidmvi.presentation.viewmodel.ViewModelTransformer1
import ru.mrfiring.covidmvi.presentation.viewmodel.ViewModelTransformer2

class MainFragmentBinder(
    private val lifecycle: LifecycleOwner,
    private val globalStatsCacheFeature: GlobalStatsCacheFeature,
    private val globalStatsFeature: GlobalStatsFeature
): AndroidBindings<MainFragment>(lifecycle) {
    override fun setup(view: MainFragment) {
        binder.bind(globalStatsCacheFeature to view using ViewModelTransformer2())
        binder.bind( globalStatsFeature to view using ViewModelTransformer1())
    }
}