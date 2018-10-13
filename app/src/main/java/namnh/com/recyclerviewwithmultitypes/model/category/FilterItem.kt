package namnh.com.recyclerviewwithmultitypes.model.category

import namnh.com.recyclerviewwithmultitypes.adapter.RecyclerType
import namnh.com.recyclerviewwithmultitypes.adapter.RecyclerViewItem
import namnh.com.recyclerviewwithmultitypes.adapter.TYPE_FILTER_ITEM

class FilterItem(var image: Int, var filterName: String?) : RecyclerViewItem {
    @RecyclerType
    override fun getType(): Int {
        return TYPE_FILTER_ITEM
    }

    override fun equals(other: Any?): Boolean {
        if (other !is FilterItem) return false
        return image == other.image && filterName?.equals(other.filterName) ?: false
    }

    override fun hashCode(): Int {
        var result = image
        result = 31 * result + (filterName?.hashCode() ?: 0)
        return result
    }
}
