package net.bouzuya.nekurabe.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import net.bouzuya.nekurabe.R
import net.bouzuya.nekurabe.data.EventObserver
import net.bouzuya.nekurabe.databinding.PriceDetailFragmentBinding
import net.bouzuya.nekurabe.ui.PriceDetailFragmentDirections.Companion.actionPriceDetailFragmentToPriceEditFragment
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class PriceDetailFragment : Fragment() {
    private val args: PriceDetailFragmentArgs by navArgs()
    private val viewModel: PriceDetailViewModel by inject { parametersOf(args.priceId) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = PriceDetailFragmentBinding.inflate(inflater, container, false).also { binding ->
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        viewModel.deleteCompleteEvent.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigateUp()
        })
        viewModel.editEvent.observe(viewLifecycleOwner, EventObserver { priceId ->
            findNavController().navigate(actionPriceDetailFragmentToPriceEditFragment(priceId))
        })

        setHasOptionsMenu(true) // onCreateOptionsMenu
    }.root

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.price_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.price_detail_destroy) {
            viewModel.destroy()
            true
        } else super.onOptionsItemSelected(item)
    }
}
