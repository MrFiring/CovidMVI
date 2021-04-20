package ru.mrfiring.covidmvi.presentation.ui.globaldetailscreen

import androidx.lifecycle.LifecycleOwner
import com.badoo.mvicore.android.AndroidBindings
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.mrfiring.covidmvi.presentation.features.GlobalStatsFeature

class GlobalDetailFragmentBinder @AssistedInject constructor(
    @Assisted private val lifecycle: LifecycleOwner,
    private val globalStatsFeature: GlobalStatsFeature
): AndroidBindings<GlobalDetailFragment>(lifecycle) {

    override fun setup(view: GlobalDetailFragment) {
        TODO("Not yet implemented")
    }


    @AssistedFactory
    interface GlobalFragmentBinderFactory {
        fun create(lifecycle: LifecycleOwner): GlobalDetailFragmentBinder
    }
}