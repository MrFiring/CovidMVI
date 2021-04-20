package ru.mrfiring.covidmvi.presentation.ui.globaldetailscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import ru.mrfiring.covidmvi.databinding.FragmentGlobalBinding
import ru.mrfiring.covidmvi.presentation.util.ObservableSourceFragment
import java.util.function.Consumer
import javax.inject.Inject


@AndroidEntryPoint
class GlobalDetailFragment : ObservableSourceFragment<Nothing>(), Consumer<ViewModel> {

    @Inject
    lateinit var globalDetailFragmentBinderFactory: GlobalDetailFragmentBinder.GlobalFragmentBinderFactory

    private lateinit var binderDetail: GlobalDetailFragmentBinder

    private lateinit var binding: FragmentGlobalBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGlobalBinding.inflate(inflater, container, false)

        binderDetail = globalDetailFragmentBinderFactory.create(this)
        binderDetail.setup(this)


        return binding.root
    }

    override fun accept(t: ViewModel) {
        TODO("Implement this some how")
    }
}