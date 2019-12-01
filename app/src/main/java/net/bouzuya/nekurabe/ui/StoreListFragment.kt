package net.bouzuya.nekurabe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import net.bouzuya.nekurabe.data.EventObserver
import net.bouzuya.nekurabe.data.NekurabeDatabase
import net.bouzuya.nekurabe.data.StoreRepository
import net.bouzuya.nekurabe.databinding.StoreListFragmentBinding
import net.bouzuya.nekurabe.ui.StoreListFragmentDirections.Companion.actionStoreListFragmentToStoreEditFragment

class StoreListFragment : Fragment() {
    private val viewModel: StoreListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                StoreListViewModel(
                    StoreRepository(
                        NekurabeDatabase.getDatabase(requireContext()).storeDao()
                    )
                ) as T
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = StoreListFragmentBinding.inflate(inflater, container, false).also { binding ->
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.createEvent.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(actionStoreListFragmentToStoreEditFragment())
        })
    }.root
}
