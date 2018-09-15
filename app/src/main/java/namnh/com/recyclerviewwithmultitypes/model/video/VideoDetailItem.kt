package namnh.com.recyclerviewwithmultitypes.model.video

import namnh.com.recyclerviewwithmultitypes.adapter.RecyclerViewItem
import namnh.com.recyclerviewwithmultitypes.adapter.RecyclerViewSupportedType
import namnh.com.recyclerviewwithmultitypes.adapter.TYPE_VIDEO_ITEM

class VideoDetailItem(var image: Int, var videoName: String?, val uploader: String,
    val videoViewedNumber: Int) : RecyclerViewItem {
    @RecyclerViewSupportedType
    override fun getType(): Int {
        return TYPE_VIDEO_ITEM
    }
}
