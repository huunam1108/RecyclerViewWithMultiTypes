package namnh.com.recyclerviewwithmultitypes

import android.view.ViewGroup
import namnh.com.recyclerviewwithmultitypes.adapter.*
import namnh.com.recyclerviewwithmultitypes.model.banner.BannerItem
import namnh.com.recyclerviewwithmultitypes.model.banner.BannerViewHolder
import namnh.com.recyclerviewwithmultitypes.model.category.FilterCategoryItem
import namnh.com.recyclerviewwithmultitypes.model.category.FilterCategoryViewHolder
import namnh.com.recyclerviewwithmultitypes.model.video.GridVideoDetailItem
import namnh.com.recyclerviewwithmultitypes.model.video.GridVideoDetailViewHolder

class DemoAdapter : BaseAdapter(diffUtil) {

    override fun customViewHolder(parent: ViewGroup, viewType: Int): Any {
        return when (viewType) {
            TYPE_BANNER -> BannerViewHolder(inflateView(R.layout.item_banner, parent))
            TYPE_LIST_FILTER -> FilterCategoryViewHolder(
                inflateView(R.layout.item_filter_category, parent))
            else ->
                GridVideoDetailViewHolder(inflateView(R.layout.item_video_category, parent))
        }
    }

    companion object {
        private val diffUtil = object : BaseDiffUtil<RecyclerViewItem>() {
            override fun areItemsTheSame(oldItem: RecyclerViewItem,
                newItem: RecyclerViewItem): Boolean {
                return true
            }

            override fun areContentsTheSame(oldItem: RecyclerViewItem,
                newItem: RecyclerViewItem): Boolean {
                when (oldItem) {
                    is BannerItem -> {
                        return newItem is BannerItem && newItem == oldItem
                    }
                    is FilterCategoryItem -> {
                        return newItem is FilterCategoryItem && newItem == oldItem
                    }
                    is GridVideoDetailItem -> {
                        return newItem is GridVideoDetailItem && newItem == oldItem
                    }
                }
                return oldItem == newItem
            }
        }
    }
}
