package net.bouzuya.nekurabe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import net.bouzuya.nekurabe.databinding.PriceDetailFragmentBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class PriceDetailFragment : Fragment() {
    private val args: PriceDetailFragmentArgs by navArgs()
    private val viewModel: PriceDetailViewModel by inject { parametersOf(args.priceId) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = PriceDetailFragmentBinding.inflate(inflater, container, false).also { binding ->
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }.root
}
