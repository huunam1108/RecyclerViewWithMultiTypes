package namnh.com.recyclerviewwithmultitypes.model.video

import android.support.annotation.DrawableRes
import namnh.com.recyclerviewwithmultitypes.adapter.RecyclerType
import namnh.com.recyclerviewwithmultitypes.adapter.RecyclerViewItem
import namnh.com.recyclerviewwithmultitypes.adapter.TYPE_GRID_VIDEO
import namnh.com.recyclerviewwithmultitypes.adapter.TYPE_UNKNOWN

class GridVideoDetailItem(@DrawableRes var image: Int, var categoryName: String?,
    val videoList: List<VideoDetailItem>) : RecyclerViewItem {
    @RecyclerType
    override var type: Int = TYPE_UNKNOWN
        get() = TYPE_GRID_VIDEO

    override fun hashCode(): Int {
        return image.hashCode() + videoList.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is GridVideoDetailItem) return false
        return this === other
    }
}
