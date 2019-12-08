package net.bouzuya.nekurabe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import net.bouzuya.nekurabe.data.EventObserver
import net.bouzuya.nekurabe.databinding.StoreEditFragmentBinding
import net.bouzuya.nekurabe.ui.StoreEditFragmentDirections.Companion.actionStoreEditFragmentToStoreDetailFragment
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class StoreEditFragment : Fragment() {
    private val args: StoreEditFragmentArgs by navArgs()
    private val viewModel: StoreEditViewModel by inject { parametersOf(args.storeId) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = StoreEditFragmentBinding.inflate(inflater, container, false).also { binding ->
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.savedEvent.observe(viewLifecycleOwner, EventObserver { savedId ->
            findNavController().navigate(actionStoreEditFragmentToStoreDetailFragment(savedId))
        })
    }.root
}
