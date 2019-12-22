package net.bouzuya.nekurabe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import net.bouzuya.nekurabe.databinding.ItemEditFragmentBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ItemEditFragment : Fragment() {
    private val args: ItemEditFragmentArgs by navArgs()
    private val viewModel: ItemEditViewModel by inject { parametersOf(args.itemId) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = ItemEditFragmentBinding.inflate(inflater, container, false).also { binding ->
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }.root
}
