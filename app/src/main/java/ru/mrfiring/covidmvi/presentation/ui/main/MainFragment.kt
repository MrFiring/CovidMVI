package ru.mrfiring.covidmvi.presentation.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.functions.Consumer
import ru.mrfiring.covidmvi.R
import ru.mrfiring.covidmvi.databinding.FragmentMainBinding
import ru.mrfiring.covidmvi.presentation.event.UiEvent
import ru.mrfiring.covidmvi.presentation.util.ObservableSourceFragment


@AndroidEntryPoint
class MainFragment : ObservableSourceFragment<UiEvent>(), Consumer<ViewModel> {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun accept(t: ViewModel?) {
        TODO("Not yet implemented")
    }
}