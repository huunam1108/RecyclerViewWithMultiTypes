package namnh.com.recyclerviewwithmultitypes.model.video

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import namnh.com.recyclerviewwithmultitypes.R
import namnh.com.recyclerviewwithmultitypes.adapter.BaseViewHolder
import namnh.com.recyclerviewwithmultitypes.util.GridSpacingItemDecoration


class GridVideoDetailViewHolder(itemView: View) : BaseViewHolder<GridVideoDetailItem>(itemView) {
    private var tvTitle: TextView = itemView.findViewById(R.id.text_title_category)
    private var videoRecyclerView: RecyclerView = itemView.findViewById(R.id.grid_video)
    private var videoDetailAdapter: VideoDetailAdapter? = null

    init {
        val layoutManager = GridLayoutManager(itemView.context, 2)
        layoutManager.initialPrefetchItemCount = 4
        videoRecyclerView.layoutManager = layoutManager
        videoRecyclerView.addItemDecoration(GridSpacingItemDecoration(2, 20, false))
    }

    override fun bind(item: GridVideoDetailItem) {
        tvTitle.text = item.categoryName
        if (videoDetailAdapter == null) {
            videoDetailAdapter = VideoDetailAdapter(item.videoList)
            videoRecyclerView.adapter = videoDetailAdapter
        } else {
            videoDetailAdapter!!.submitList(item.videoList)
        }
    }
}
    