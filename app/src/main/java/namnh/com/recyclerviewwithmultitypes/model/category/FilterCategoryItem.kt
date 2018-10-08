package namnh.com.recyclerviewwithmultitypes.model.category

import namnh.com.recyclerviewwithmultitypes.adapter.RecyclerType
import namnh.com.recyclerviewwithmultitypes.adapter.RecyclerViewItem
import namnh.com.recyclerviewwithmultitypes.adapter.TYPE_LIST_FILTER
import namnh.com.recyclerviewwithmultitypes.adapter.TYPE_UNKNOWN

class FilterCategoryItem(val filterList: List<FilterItem>) : RecyclerViewItem {
    @RecyclerType
    override var type: Int = TYPE_UNKNOWN
        get() = TYPE_LIST_FILTER

    override fun hashCode(): Int {
        return filterList.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is FilterCategoryItem) return false
        return filterList == other.filterList
    }
}
