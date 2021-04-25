package ru.mrfiring.covidmvi.presentation.ui.globaldetailscreen

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import ru.mrfiring.covidmvi.databinding.FragmentGlobalBinding
import ru.mrfiring.covidmvi.domain.DomainGlobalHistoricalStats
import ru.mrfiring.covidmvi.domain.ResolutionType
import ru.mrfiring.covidmvi.presentation.event.UiEvent
import ru.mrfiring.covidmvi.presentation.ui.reusable.detailstats.StatsListViewAdapter
import ru.mrfiring.covidmvi.presentation.util.ObservableSourceFragment
import ru.mrfiring.covidmvi.presentation.viewmodel.ViewModel
import javax.inject.Inject


@AndroidEntryPoint
class GlobalDetailFragment : ObservableSourceFragment<UiEvent>(), Consumer<ViewModel.GlobalDetail> {

    @Inject
    lateinit var globalDetailFragmentBinderFactory: GlobalDetailFragmentBinder.GlobalFragmentBinderFactory

    private lateinit var binderDetail: GlobalDetailFragmentBinder

    private lateinit var binding: FragmentGlobalBinding

    private lateinit var adapter: StatsListViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGlobalBinding.inflate(inflater, container, false)

        binderDetail = globalDetailFragmentBinderFactory.create(this)
        binderDetail.setup(this)

        initChart()

        adapter = StatsListViewAdapter()
        binding.globalDetailList.adapter = adapter

        //Setup ui-events.
        with(binding.globalGraphWithButtons){
            chartResolutionTwoWeeks.setOnClickListener {
                onNext(
                    UiEvent.ChartFilterButtonClicked(
                        ResolutionType.TWO_WEEKS
                    )
                )
            }

            chartResolutionOneMonth.setOnClickListener {
                onNext(
                    UiEvent.ChartFilterButtonClicked(
                        ResolutionType.ONE_MONTH
                    )
                )
            }

            chartResolutionSixMonths.setOnClickListener {
                onNext(
                    UiEvent.ChartFilterButtonClicked(
                        ResolutionType.SIX_MONTHS
                    )
                )
            }

            chartResolutionOneYear.setOnClickListener {
                onNext(
                    UiEvent.ChartFilterButtonClicked(
                        ResolutionType.ONE_YEAR
                    )
                )
            }

            chartResolutionAll.setOnClickListener {
                onNext(
                    UiEvent.ChartFilterButtonClicked(
                        ResolutionType.ALL
                    )
                )
            }
        }


        return binding.root
    }

    override fun accept(t: ViewModel.GlobalDetail?) {
        t?.let {
            binding.globalDetailProgressBar.visibility = if (it.isLoading) {
                View.VISIBLE
            } else {
                View.GONE
            }

            it.historicalStats?.let { data ->
                submitChartData(data)
            }

            it.globalStats?.let { stats ->
                Single.fromCallable { stats.asDataItemList() }
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { list ->
                        adapter.submitList(list)
                    }
            }

        }
    }


    private fun initChart() {
        with(binding.globalGraphWithButtons) {
            chartGraph.setDrawGridBackground(true)
            chartGraph.isAutoScaleMinMaxEnabled = true
        }
    }

    private fun submitChartData(data: DomainGlobalHistoricalStats) {
        val casesSingle = Single.just(
            data.cases
                .toList()
                .mapIndexed { index, pair ->
                    Entry(
                        index.toFloat(),
                        pair.second.toFloat(),
                        pair.first
                    )
                }
        ).map {
            val dataSet = LineDataSet(it, "Cases")
            dataSet.setDrawCircles(false)
            dataSet.color = ColorTemplate.getHoloBlue()
            dataSet.lineWidth = 2f
            dataSet.setDrawValues(false)
            return@map dataSet
        }

        val recoveredSingle = Single.just(
            data.recovered
                .toList()
                .mapIndexed { index, pair ->
                    Entry(
                        index.toFloat(),
                        pair.second.toFloat(),
                        pair.first
                    )
                }
        ).map {
            val dataSet = LineDataSet(it, "Recovered")
            dataSet.setDrawCircles(false)
            dataSet.color = Color.GREEN
            dataSet.lineWidth = 2f
            dataSet.setDrawValues(false)
            return@map dataSet
        }

        val deathsSingle = Single.just(
            data.deaths
                .toList()
                .mapIndexed { index, pair ->
                    Entry(
                        index.toFloat(),
                        pair.second.toFloat(),
                        pair.first
                    )
                }
        ).map {
            val dataSet = LineDataSet(it, "Deaths")
            dataSet.setDrawCircles(false)
            dataSet.color = Color.BLACK
            dataSet.lineWidth = 2f
            dataSet.setDrawValues(false)
            return@map dataSet
        }


        val lineData = LineData()
        Single.merge(casesSingle, recoveredSingle, deathsSingle)
            .observeOn(Schedulers.computation())
            .doOnEach {
                it.value?.let { dataSet ->
                    lineData.addDataSet(dataSet)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                with(binding.globalGraphWithButtons){
                    chartGraph.data = lineData
                    chartGraph.invalidate()
                }
            }
            .subscribe()
    }

}