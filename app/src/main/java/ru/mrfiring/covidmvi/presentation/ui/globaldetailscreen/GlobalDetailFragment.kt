package ru.mrfiring.covidmvi.presentation.ui.globaldetailscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import ru.mrfiring.covidmvi.databinding.FragmentGlobalBinding
import ru.mrfiring.covidmvi.presentation.event.UiEvent
import ru.mrfiring.covidmvi.presentation.ui.reusable.detailstats.StatsListViewAdapter
import ru.mrfiring.covidmvi.presentation.ui.reusable.detailstats.asDataItemList
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

        adapter = StatsListViewAdapter()
        binding.globalDetailList.adapter = adapter

        return binding.root
    }

    override fun accept(t: ViewModel.GlobalDetail?) {
        t?.let {
            binding.globalDetailProgressBar.visibility = if (it.isLoading) {
                View.VISIBLE
            } else {
                View.GONE
            }

            it.globalStats?.let { stats ->
                Completable.fromAction {
                    adapter.submitList(
                        stats.asDataItemList()
                    )
                }
                    .subscribeOn(Schedulers.computation())
                    .subscribe()
            }

        }
    }
}