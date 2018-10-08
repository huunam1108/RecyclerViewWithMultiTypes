package namnh.com.recyclerviewwithmultitypes.model.video

import namnh.com.recyclerviewwithmultitypes.adapter.RecyclerType
import namnh.com.recyclerviewwithmultitypes.adapter.RecyclerViewItem
import namnh.com.recyclerviewwithmultitypes.adapter.TYPE_UNKNOWN
import namnh.com.recyclerviewwithmultitypes.adapter.TYPE_VIDEO_ITEM

class VideoDetailItem(var image: Int, var videoName: String?, val uploader: String,
    val videoViewedNumber: Int) : RecyclerViewItem {
    @RecyclerType
    override var type: Int = TYPE_UNKNOWN
        get() = TYPE_VIDEO_ITEM

    override fun equals(other: Any?): Boolean {
        if (other !is VideoDetailItem) return false

        return image == other.image && videoName?.equals(
            other.videoName) ?: false && uploader == other.uploader && videoViewedNumber == videoViewedNumber
    }

    override fun hashCode(): Int {
        var result = image
        result = 31 * result + (videoName?.hashCode() ?: 0)
        result = 31 * result + uploader.hashCode()
        result = 31 * result + videoViewedNumber
        return result
    }
}
