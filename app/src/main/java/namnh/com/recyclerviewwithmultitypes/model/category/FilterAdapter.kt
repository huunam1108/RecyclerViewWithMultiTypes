package namnh.com.recyclerviewwithmultitypes.model.category

import android.view.ViewGroup
import namnh.com.recyclerviewwithmultitypes.R
import namnh.com.recyclerviewwithmultitypes.adapter.BaseAdapter
import namnh.com.recyclerviewwithmultitypes.adapter.BaseDiffUtil

class FilterAdapter : BaseAdapter(diffUtil) {

    override fun customViewHolder(parent: ViewGroup, viewType: Int): Any {
        return FilterViewHolder(inflateView(R.layout.item_filter, parent))
    }

    companion object {
        private val diffUtil = object : BaseDiffUtil<FilterItem>() {
            override fun areItemsTheSame(oldItem: FilterItem, newItem: FilterItem): Boolean {
                return true
            }

            override fun areContentsTheSame(oldItem: FilterItem, newItem: FilterItem): Boolean {
                return newItem == oldItem
            }
        }
    }
}
