package namnh.com.recyclerviewwithmultitypes.model.category

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import namnh.com.recyclerviewwithmultitypes.R
import namnh.com.recyclerviewwithmultitypes.adapter.BaseViewHolder
import namnh.com.recyclerviewwithmultitypes.util.HorizontalSpaceItemDecoration


class FilterCategoryViewHolder(itemView: View) : BaseViewHolder<FilterCategoryItem>(itemView) {
    private var filterRecyclerView: RecyclerView = itemView.findViewById(R.id.recycler_filter)
    private var filterAdapter: FilterAdapter? = null

    init {
        filterRecyclerView.layoutManager = LinearLayoutManager(itemView.context,
            RecyclerView.HORIZONTAL, false)
        filterRecyclerView.isNestedScrollingEnabled = false
        filterRecyclerView.setHasFixedSize(true)
        filterRecyclerView.addItemDecoration(HorizontalSpaceItemDecoration(20))
    }

    override fun bind(item: FilterCategoryItem) {
        super.bind(item)
        if (filterAdapter == null) {
            filterAdapter = FilterAdapter(item.filterList)
            filterRecyclerView.adapter = filterAdapter
        } else {
            filterAdapter!!.filterList = item.filterList
        }
    }
}
