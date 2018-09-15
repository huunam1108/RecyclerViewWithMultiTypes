package namnh.com.recyclerviewwithmultitypes.model.category

import namnh.com.recyclerviewwithmultitypes.adapter.RecyclerViewItem
import namnh.com.recyclerviewwithmultitypes.adapter.RecyclerViewSupportedType
import namnh.com.recyclerviewwithmultitypes.adapter.TYPE_LIST_FILTER

class FilterCategoryItem(val filterList: List<FilterItem>) : RecyclerViewItem {
    @RecyclerViewSupportedType
    override fun getType(): Int {
        return TYPE_LIST_FILTER
    }
}
