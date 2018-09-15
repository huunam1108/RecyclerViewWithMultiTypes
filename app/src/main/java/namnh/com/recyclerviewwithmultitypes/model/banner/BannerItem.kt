package namnh.com.recyclerviewwithmultitypes.model.banner

import namnh.com.recyclerviewwithmultitypes.adapter.RecyclerViewItem
import namnh.com.recyclerviewwithmultitypes.adapter.RecyclerViewSupportedType
import namnh.com.recyclerviewwithmultitypes.adapter.TYPE_BANNER
import namnh.com.recyclerviewwithmultitypes.pager.PagerColorItem

class BannerItem(val images: List<PagerColorItem>) : RecyclerViewItem {

    @RecyclerViewSupportedType
    override fun getType(): Int {
        return TYPE_BANNER
    }

    override fun hashCode(): Int {
        return images.hashCode()
    }
}
