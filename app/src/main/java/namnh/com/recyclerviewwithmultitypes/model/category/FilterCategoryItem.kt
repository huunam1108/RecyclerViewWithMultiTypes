package namnh.com.recyclerviewwithmultitypes.model.category

import namnh.com.recyclerviewwithmultitypes.adapter.RecyclerType
import namnh.com.recyclerviewwithmultitypes.adapter.RecyclerViewItem
import namnh.com.recyclerviewwithmultitypes.adapter.TYPE_LIST_FILTER

class FilterCategoryItem(val filterList: List<FilterItem>) : RecyclerViewItem {
    @RecyclerType
    override fun getType(): Int {
        return TYPE_LIST_FILTER
    }

    override fun hashCode(): Int {
        return filterList.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FilterCategoryItem) return false

        if (filterList != other.filterList) return false

        return true
    }
}
