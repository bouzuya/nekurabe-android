package net.bouzuya.nekurabe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import net.bouzuya.nekurabe.databinding.StoreEditFragmentBinding
import org.koin.android.ext.android.inject

class StoreEditFragment : Fragment() {
    private val viewModel: StoreEditViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = StoreEditFragmentBinding.inflate(inflater, container, false).also { binding ->
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }.root
}
