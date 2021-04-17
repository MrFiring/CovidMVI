package ru.mrfiring.covidmvi.presentation.ui.globalscreen

import androidx.lifecycle.LifecycleOwner
import com.badoo.mvicore.android.AndroidBindings
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.mrfiring.covidmvi.presentation.features.GlobalStatsFeature

class GlobalFragmentBinder @AssistedInject constructor(
    @Assisted private val lifecycle: LifecycleOwner,
    private val globalStatsFeature: GlobalStatsFeature
): AndroidBindings<GlobalFragment>(lifecycle) {

    override fun setup(view: GlobalFragment) {
        TODO("Not yet implemented")
    }


    @AssistedFactory
    interface GlobalFragmentBinderFactory {
        fun create(lifecycle: LifecycleOwner): GlobalFragmentBinder
    }
}