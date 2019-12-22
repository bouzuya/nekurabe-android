package net.bouzuya.nekurabe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import net.bouzuya.nekurabe.data.EventObserver
import net.bouzuya.nekurabe.databinding.ItemListFragmentBinding
import net.bouzuya.nekurabe.ui.ItemListFragmentDirections.Companion.actionItemListFragmentToItemEditFragment
import org.koin.android.ext.android.inject

class ItemListFragment : Fragment() {
    private val viewModel: ItemListViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = ItemListFragmentBinding.inflate(inflater, container, false).also { binding ->
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.newEvent.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(actionItemListFragmentToItemEditFragment(0L))
        })
    }.root
}
