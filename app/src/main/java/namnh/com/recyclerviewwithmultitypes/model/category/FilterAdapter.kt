package namnh.com.recyclerviewwithmultitypes.model.category

import android.view.ViewGroup
import namnh.com.recyclerviewwithmultitypes.R
import namnh.com.recyclerviewwithmultitypes.adapter.BaseAdapter
import namnh.com.recyclerviewwithmultitypes.adapter.BaseDiffUtil
import namnh.com.recyclerviewwithmultitypes.adapter.BaseViewHolder
import namnh.com.recyclerviewwithmultitypes.adapter.RecyclerViewItem

class FilterAdapter(var filterList: List<FilterItem>) : BaseAdapter(filterList,
    DIFF_CALLBACK) {

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup,
        viewType: Int): BaseViewHolder<in RecyclerViewItem> {
        return FilterViewHolder(
            inflateView(R.layout.item_filter, parent)) as BaseViewHolder<in RecyclerViewItem>
    }

    companion object {
        private val DIFF_CALLBACK = object : BaseDiffUtil<RecyclerViewItem>() {
            override fun areItemsTheSame(oldItem: RecyclerViewItem,
                newItem: RecyclerViewItem): Boolean {
                return true
            }

            override fun areContentsTheSame(oldItem: RecyclerViewItem,
                newItem: RecyclerViewItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
