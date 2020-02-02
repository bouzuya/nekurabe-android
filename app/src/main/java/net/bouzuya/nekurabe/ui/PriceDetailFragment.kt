package net.bouzuya.nekurabe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import net.bouzuya.nekurabe.databinding.PriceDetailFragmentBinding
import org.koin.android.ext.android.inject

class PriceDetailFragment : Fragment() {
    private val viewModel: PriceDetailViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = PriceDetailFragmentBinding.inflate(inflater, container, false).also { binding ->
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }.root
}
