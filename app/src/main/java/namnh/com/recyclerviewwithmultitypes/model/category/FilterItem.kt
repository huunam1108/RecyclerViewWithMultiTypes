package namnh.com.recyclerviewwithmultitypes.model.category

import namnh.com.recyclerviewwithmultitypes.adapter.RecyclerViewItem
import namnh.com.recyclerviewwithmultitypes.adapter.RecyclerViewSupportedType
import namnh.com.recyclerviewwithmultitypes.adapter.TYPE_FILTER_ITEM

class FilterItem(var image: Int, var filterName: String?) : RecyclerViewItem {
    @RecyclerViewSupportedType
    override fun getType(): Int {
        return TYPE_FILTER_ITEM
    }
}
