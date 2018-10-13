package namnh.com.recyclerviewwithmultitypes.model.banner

import namnh.com.recyclerviewwithmultitypes.adapter.RecyclerType
import namnh.com.recyclerviewwithmultitypes.adapter.RecyclerViewItem
import namnh.com.recyclerviewwithmultitypes.adapter.TYPE_BANNER
import namnh.com.recyclerviewwithmultitypes.pager.PagerColorItem

class BannerItem(val images: List<PagerColorItem>) : RecyclerViewItem {

    @RecyclerType
    override fun getType(): Int {
        return TYPE_BANNER
    }

    override fun hashCode(): Int {
        return images.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BannerItem) return false

        if (images != other.images) return false

        return true
    }
}
