package net.bouzuya.nekurabe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import net.bouzuya.nekurabe.data.EventObserver
import net.bouzuya.nekurabe.databinding.PriceEditFragmentBinding
import net.bouzuya.nekurabe.ui.PriceEditFragmentDirections.Companion.actionPriceEditFragmentToPriceDetailFragment
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class PriceEditFragment : Fragment() {
    private val args: PriceEditFragmentArgs by navArgs()
    private val viewModel: PriceEditViewModel by inject { parametersOf(args.priceId) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = PriceEditFragmentBinding.inflate(inflater, container, false).also { binding ->
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.savedEvent.observe(viewLifecycleOwner, EventObserver { savedId ->
            findNavController().navigate(actionPriceEditFragmentToPriceDetailFragment(savedId))
        })
    }.root
}
