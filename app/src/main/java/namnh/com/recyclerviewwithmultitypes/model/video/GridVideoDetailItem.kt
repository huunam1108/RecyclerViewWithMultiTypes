package namnh.com.recyclerviewwithmultitypes.model.video

import android.support.annotation.DrawableRes
import namnh.com.recyclerviewwithmultitypes.adapter.RecyclerViewItem
import namnh.com.recyclerviewwithmultitypes.adapter.RecyclerViewSupportedType
import namnh.com.recyclerviewwithmultitypes.adapter.TYPE_GRID_VIDEO

class GridVideoDetailItem(@DrawableRes var image: Int, var categoryName: String?,
    val videoList: List<VideoDetailItem>) : RecyclerViewItem {
    @RecyclerViewSupportedType
    override fun getType(): Int {
        return TYPE_GRID_VIDEO
    }

    override fun hashCode(): Int {
        return image.hashCode() + videoList.hashCode()
    }
}
