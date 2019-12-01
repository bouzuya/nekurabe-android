package net.bouzuya.nekurabe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import net.bouzuya.nekurabe.data.EventObserver
import net.bouzuya.nekurabe.databinding.HomeFragmentBinding
import net.bouzuya.nekurabe.ui.HomeFragmentDirections.Companion.actionHomeFragmentToStoreListFragment
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = HomeFragmentBinding.inflate(inflater, container, false).also { binding ->
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.navigateStoreListEvent.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(actionHomeFragmentToStoreListFragment())
        })
    }.root
}
