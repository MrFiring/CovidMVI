package ru.mrfiring.covidmvi.presentation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.functions.Consumer
import ru.mrfiring.covidmvi.R
import ru.mrfiring.covidmvi.databinding.FragmentMainBinding
import ru.mrfiring.covidmvi.presentation.event.UiEvent
import ru.mrfiring.covidmvi.presentation.features.GlobalStatsCacheFeature
import ru.mrfiring.covidmvi.presentation.features.GlobalStatsFeature
import ru.mrfiring.covidmvi.presentation.util.ObservableSourceFragment
import ru.mrfiring.covidmvi.presentation.viewmodel.ViewModel
import javax.inject.Inject


@AndroidEntryPoint
class MainFragment : ObservableSourceFragment<UiEvent>(), Consumer<ViewModel> {

    @Inject
    lateinit var globalStatsFeature: GlobalStatsFeature
    @Inject
    lateinit var globalStatsCacheFeature: GlobalStatsCacheFeature

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        val binder = MainFragmentBinder(
            this,
            globalStatsCacheFeature,
            globalStatsFeature
        )
        binder.setup(this)

        return binding.root
    }


    override fun accept(t: ViewModel?) {
        t?.let {
            binding.apply {
                mainTotalCases.text = it.globalStats.cases.toString()
                mainProgressBar.visibility = if(it.isLoading) View.VISIBLE else View.GONE
            }
        }
    }
}