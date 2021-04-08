package ru.mrfiring.covidmvi.presentation.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.functions.Consumer
import ru.mrfiring.covidmvi.R
import ru.mrfiring.covidmvi.databinding.FragmentMainBinding
import ru.mrfiring.covidmvi.presentation.event.UiEvent
import ru.mrfiring.covidmvi.presentation.util.ObservableSourceFragment
import ru.mrfiring.covidmvi.presentation.viewmodel.ViewModel
import javax.inject.Inject


@AndroidEntryPoint
class MainFragment : ObservableSourceFragment<UiEvent>(), Consumer<ViewModel> {

    @Inject
    lateinit var mainBinderFactory: MainFragmentBinder.MainFragmentBinderFactory

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        val binder = mainBinderFactory.create(this)
        binder.setup(this)

        return binding.root
    }


    override fun accept(t: ViewModel?) {
        t?.let {
            binding.apply {
                //cases
                mainTotalCases.text = it.globalStats.cases.toString()
                mainTodayCases.text = it.globalStats.todayCases.toString()

                //recovered
                mainTotalRecovered.text = it.globalStats.recovered.toString()
                mainTodayRecovered.text = it.globalStats.todayRecovered.toString()

                //deaths
                mainTotalDeaths.text = it.globalStats.deaths.toString()
                mainTodayDeaths.text = it.globalStats.todayDeaths.toString()

                mainProgressBar.visibility = if(it.isLoading) View.VISIBLE else View.GONE
            }
        }
    }
}