package ru.mrfiring.covidmvi.presentation

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.functions.Consumer
import ru.mrfiring.covidmvi.R
import ru.mrfiring.covidmvi.databinding.ActivityMainBinding
import ru.mrfiring.covidmvi.presentation.event.UiEvent
import ru.mrfiring.covidmvi.presentation.features.GlobalStatsFeature
import ru.mrfiring.covidmvi.presentation.viewmodel.ViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ObservableSourceActivity<UiEvent>(), Consumer<ViewModel> {

@Inject
lateinit var feature: GlobalStatsFeature

    lateinit var binder: MainActivityBinder

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binder = MainActivityBinder(this, feature)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLoad.setOnClickListener {
            onNext(UiEvent.ButtonClicked)
        }

        binder.setup(this)
    }

    override fun accept(t: ViewModel) {
        with(binding){
            confirmedText.text = t.globalStats.totalConfirmed.toString()
            deathsText.text = t.globalStats.totalDeaths.toString()
            recoveredText.text = t.globalStats.totalRecovered.toString()
            btnLoad.visibility = if(t.isLoading) View.GONE else View.VISIBLE
            progressBar.visibility = if(t.isLoading) View.VISIBLE else View.GONE
        }
    }
}