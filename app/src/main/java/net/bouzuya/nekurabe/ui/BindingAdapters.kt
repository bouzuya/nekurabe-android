package net.bouzuya.nekurabe.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.bouzuya.nekurabe.data.Store
import net.bouzuya.nekurabe.databinding.StoreListItemBinding

@BindingAdapter(
    "storeList"
)
fun RecyclerView.setStoreList(
    storeList: List<Store>?
) {
    class BindingViewHolder(val binding: StoreListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    class StoreListAdapter : RecyclerView.Adapter<BindingViewHolder>() {
        private var _dataSet: List<Store> = emptyList()
        var dataSet: List<Store>
            get() = _dataSet
            set(values) {
                _dataSet = values
                notifyDataSetChanged()
            }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder =
            BindingViewHolder(
                StoreListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

        override fun getItemCount(): Int = dataSet.size

        override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
            holder.binding.item = dataSet[position]
        }
    }

    if (adapter == null) {
        layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapter = StoreListAdapter()
    }
    val itemList = storeList ?: emptyList()
    (adapter as? StoreListAdapter)?.dataSet = itemList
}
