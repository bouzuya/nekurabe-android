package net.bouzuya.nekurabe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.bouzuya.nekurabe.data.NekurabeDatabase
import net.bouzuya.nekurabe.data.StoreRepository
import net.bouzuya.nekurabe.databinding.StoreEditFragmentBinding

class StoreEditFragment : Fragment() {
    private val viewModel: StoreEditViewModel by viewModels {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                StoreEditViewModel(
                    StoreRepository(
                        NekurabeDatabase.getDatabase(requireContext()).storeDao()
                    )
                ) as T
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = StoreEditFragmentBinding.inflate(inflater, container, false).also { binding ->
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }.root
}
