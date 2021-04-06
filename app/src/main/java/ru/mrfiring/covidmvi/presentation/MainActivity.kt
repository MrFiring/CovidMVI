package ru.mrfiring.covidmvi.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.functions.Consumer
import ru.mrfiring.covidmvi.databinding.ActivityMainBinding
import ru.mrfiring.covidmvi.presentation.event.UiEvent
import ru.mrfiring.covidmvi.presentation.viewmodel.ViewModel
import ru.mrfiring.covidmvi.presentation.util.ObservableSourceFragment
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}