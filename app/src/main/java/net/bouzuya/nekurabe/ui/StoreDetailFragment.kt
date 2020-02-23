package net.bouzuya.nekurabe.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import net.bouzuya.nekurabe.R
import net.bouzuya.nekurabe.data.EventObserver
import net.bouzuya.nekurabe.databinding.StoreDetailFragmentBinding
import net.bouzuya.nekurabe.ui.StoreDetailFragmentDirections.Companion.actionStoreDetailFragmentToStoreEditFragment
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class StoreDetailFragment : Fragment() {
    private val args by navArgs<StoreDetailFragmentArgs>()
    private val viewModel by inject<StoreDetailViewModel> { parametersOf(args.storeId) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = StoreDetailFragmentBinding.inflate(inflater, container, false).also { binding ->
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.deleteCompleteEvent.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigateUp()
        })
        viewModel.editEvent.observe(viewLifecycleOwner, EventObserver { storeId ->
            findNavController().navigate(actionStoreDetailFragmentToStoreEditFragment(storeId))
        })

        setHasOptionsMenu(true) // onCreateOptionsMenu
    }.root

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.store_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.store_detail_destroy) {
            viewModel.destroy()
            true
        } else super.onOptionsItemSelected(item)
    }
}
