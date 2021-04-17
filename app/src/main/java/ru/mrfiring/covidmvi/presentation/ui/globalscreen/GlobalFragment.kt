package ru.mrfiring.covidmvi.presentation.ui.globalscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.ObservableSource
import ru.mrfiring.covidmvi.R
import ru.mrfiring.covidmvi.databinding.FragmentGlobalBinding
import ru.mrfiring.covidmvi.presentation.event.UiEvent
import ru.mrfiring.covidmvi.presentation.util.ObservableSourceFragment
import java.util.function.Consumer
import javax.inject.Inject


@AndroidEntryPoint
class GlobalFragment : ObservableSourceFragment<Nothing>(), Consumer<ViewModel> {

    @Inject
    lateinit var globalFragmentBinderFactory: GlobalFragmentBinder.GlobalFragmentBinderFactory

    private lateinit var binder: GlobalFragmentBinder

    private lateinit var binding: FragmentGlobalBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGlobalBinding.inflate(inflater, container, false)

        binder = globalFragmentBinderFactory.create(this)
        binder.setup(this)


        return binding.root
    }

    override fun accept(t: ViewModel) {
        TODO("Implement this some how")
    }
}