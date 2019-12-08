package net.bouzuya.nekurabe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import net.bouzuya.nekurabe.data.EventObserver
import net.bouzuya.nekurabe.databinding.StoreListFragmentBinding
import net.bouzuya.nekurabe.ui.StoreListFragmentDirections.Companion.actionStoreListFragmentToStoreDetailFragment
import net.bouzuya.nekurabe.ui.StoreListFragmentDirections.Companion.actionStoreListFragmentToStoreEditFragment
import org.koin.android.ext.android.inject
import timber.log.Timber

class StoreListFragment : Fragment() {
    private val viewModel: StoreListViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = StoreListFragmentBinding.inflate(inflater, container, false).also { binding ->
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.createEvent.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(actionStoreListFragmentToStoreEditFragment())
        })
        viewModel.showEvent.observe(viewLifecycleOwner, EventObserver { store ->
            // TODO: add param to action
            Timber.d("%d", store.id)
            findNavController().navigate(actionStoreListFragmentToStoreDetailFragment())
        })
    }.root
}
