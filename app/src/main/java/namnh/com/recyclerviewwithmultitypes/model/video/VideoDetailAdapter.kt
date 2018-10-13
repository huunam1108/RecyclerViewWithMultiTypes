package namnh.com.recyclerviewwithmultitypes.model.video

import android.view.ViewGroup
import namnh.com.recyclerviewwithmultitypes.R
import namnh.com.recyclerviewwithmultitypes.adapter.BaseAdapter
import namnh.com.recyclerviewwithmultitypes.adapter.BaseDiffUtil

class VideoDetailAdapter : BaseAdapter(diffUtil) {

    override fun customViewHolder(parent: ViewGroup, viewType: Int): Any {
        return VideoDetailViewHolder(inflateView(R.layout.item_video, parent))
    }

    companion object {
        private val diffUtil = object : BaseDiffUtil<VideoDetailItem>() {
            override fun areItemsTheSame(oldItem: VideoDetailItem,
                newItem: VideoDetailItem): Boolean {
                return true
            }

            override fun areContentsTheSame(oldItem: VideoDetailItem,
                newItem: VideoDetailItem): Boolean {
                return newItem == oldItem
            }
        }
    }
}
