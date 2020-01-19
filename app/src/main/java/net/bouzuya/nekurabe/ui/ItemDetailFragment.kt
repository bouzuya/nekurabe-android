package net.bouzuya.nekurabe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import net.bouzuya.nekurabe.data.EventObserver
import net.bouzuya.nekurabe.databinding.ItemDetailFragmentBinding
import net.bouzuya.nekurabe.ui.ItemDetailFragmentDirections.Companion.actionItemDetailFragmentToItemEditFragment
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ItemDetailFragment : Fragment() {
    private val args by navArgs<ItemDetailFragmentArgs>()
    private val viewModel by inject<ItemDetailViewModel> { parametersOf(args.itemId) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = ItemDetailFragmentBinding.inflate(inflater, container, false).also { binding ->
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.deleteCompleteEvent.observe(viewLifecycleOwner, EventObserver { itemId ->
            findNavController().navigateUp();
        })
        viewModel.editEvent.observe(viewLifecycleOwner, EventObserver { itemId ->
            findNavController().navigate(actionItemDetailFragmentToItemEditFragment(itemId))
        })
    }.root
}
