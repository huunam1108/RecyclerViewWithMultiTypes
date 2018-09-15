package namnh.com.recyclerviewwithmultitypes.model.video

import android.view.ViewGroup
import namnh.com.recyclerviewwithmultitypes.R
import namnh.com.recyclerviewwithmultitypes.adapter.BaseAdapter
import namnh.com.recyclerviewwithmultitypes.adapter.BaseDiffUtil
import namnh.com.recyclerviewwithmultitypes.adapter.BaseViewHolder
import namnh.com.recyclerviewwithmultitypes.adapter.RecyclerViewItem

class VideoDetailAdapter(var videoList: List<VideoDetailItem>) : BaseAdapter(videoList,
    DIFF_CALLBACK) {

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup,
        viewType: Int): BaseViewHolder<in RecyclerViewItem> {
        return VideoDetailViewHolder(
            inflateView(R.layout.item_video, parent)) as BaseViewHolder<in RecyclerViewItem>
    }

    companion object {
        private val DIFF_CALLBACK = object : BaseDiffUtil<RecyclerViewItem>() {
            override fun areItemsTheSame(oldUser: RecyclerViewItem,
                newUser: RecyclerViewItem): Boolean {
                return true
            }

            override fun areContentsTheSame(oldUser: RecyclerViewItem,
                newUser: RecyclerViewItem): Boolean {
                return oldUser == newUser
            }
        }
    }
}
