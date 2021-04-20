package ru.mrfiring.covidmvi.presentation.ui.globaldetailscreen

import androidx.lifecycle.LifecycleOwner
import com.badoo.binder.using
import com.badoo.mvicore.android.AndroidBindings
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.mrfiring.covidmvi.presentation.features.GlobalHistoricalStatsFeature
import ru.mrfiring.covidmvi.presentation.features.GlobalStatsFeature
import ru.mrfiring.covidmvi.presentation.viewmodel.GlobalDetailViewModelPairedTransformer
import ru.mrfiring.covidmvi.utils.combineLatest

class GlobalDetailFragmentBinder @AssistedInject constructor(
    @Assisted private val lifecycle: LifecycleOwner,
    private val globalStatsFeature: GlobalStatsFeature,
    private val globalHistoricalStatsFeature: GlobalHistoricalStatsFeature
) : AndroidBindings<GlobalDetailFragment>(lifecycle) {

    override fun setup(view: GlobalDetailFragment) {
        binder.bind(
            combineLatest(
                globalStatsFeature,
                globalHistoricalStatsFeature
            ) to view using GlobalDetailViewModelPairedTransformer()
        )
    }


    @AssistedFactory
    interface GlobalFragmentBinderFactory {
        fun create(lifecycle: LifecycleOwner): GlobalDetailFragmentBinder
    }
}