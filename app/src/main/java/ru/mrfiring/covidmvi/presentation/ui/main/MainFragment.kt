package ru.mrfiring.covidmvi.presentation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Completable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import ru.mrfiring.covidmvi.databinding.FragmentMainBinding
import ru.mrfiring.covidmvi.domain.asDomainGeneralStats
import ru.mrfiring.covidmvi.presentation.event.UiEvent
import ru.mrfiring.covidmvi.presentation.ui.reusable.GeneralStatsRecyclerViewAdapter
import ru.mrfiring.covidmvi.presentation.util.ObservableSourceFragment
import ru.mrfiring.covidmvi.presentation.viewmodel.ViewModel
import javax.inject.Inject


@AndroidEntryPoint
class MainFragment : ObservableSourceFragment<UiEvent>(), Consumer<ViewModel.Main> {

    @Inject
    lateinit var mainBinderFactory: MainFragmentBinder.MainFragmentBinderFactory

    private lateinit var binding: FragmentMainBinding

    private lateinit var adapter: GeneralStatsRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        val binder = mainBinderFactory.create(this)
        binder.setup(this)

        adapter = GeneralStatsRecyclerViewAdapter({})
        binding.mainContinentsList.adapter = adapter

        return binding.root
    }


    override fun accept(t: ViewModel.Main?) {
        t?.let { viewModel ->
            binding.apply {
                mainProgressBar.visibility = if (viewModel.isLoading) View.VISIBLE else View.GONE
                //cases
                viewModel.globalStats?.let {
                    mainTotalCases.text = it.cases.toString()
                    mainTodayCases.text = it.todayCases.toString()

                    //recovered
                    mainTotalRecovered.text = it.recovered.toString()
                    mainTodayRecovered.text = it.todayRecovered.toString()

                    //deaths
                    mainTotalDeaths.text = it.deaths.toString()
                    mainTodayDeaths.text = it.todayDeaths.toString()
                }

            }
            //recycler view setup
            t.continentStatsList?.let {
                Completable.fromAction {
                    adapter.submitList(
                        t.continentStatsList.map {
                            it.asDomainGeneralStats()
                        }
                    )
                }.subscribeOn(
                    Schedulers.computation()
                ).subscribe()
            }
        }
    }
}