package namnh.com.recyclerviewwithmultitypes

import android.view.ViewGroup
import namnh.com.recyclerviewwithmultitypes.adapter.*
import namnh.com.recyclerviewwithmultitypes.model.banner.BannerViewHolder
import namnh.com.recyclerviewwithmultitypes.model.category.FilterCategoryViewHolder
import namnh.com.recyclerviewwithmultitypes.model.video.GridVideoDetailViewHolder

class DemoAdapter(data: List<RecyclerViewItem>) : BaseAdapter(data, DIFF_CALLBACK) {

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(viewGroup: ViewGroup,
        viewType: Int): BaseViewHolder<in RecyclerViewItem> {
        return when (viewType) {
            TYPE_BANNER -> BannerViewHolder(
                inflateView(R.layout.item_banner,
                    viewGroup)) as BaseViewHolder<in RecyclerViewItem>
            TYPE_LIST_FILTER -> FilterCategoryViewHolder(
                inflateView(R.layout.item_filter_category,
                    viewGroup)) as BaseViewHolder<in RecyclerViewItem>
            else ->
                GridVideoDetailViewHolder(
                    inflateView(R.layout.item_video_category,
                        viewGroup)) as BaseViewHolder<in RecyclerViewItem>
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : BaseDiffUtil<RecyclerViewItem>() {
            override fun areItemsTheSame(oldItem: RecyclerViewItem,
                newItem: RecyclerViewItem): Boolean {
                return true
            }

            override fun areContentsTheSame(oldItem: RecyclerViewItem,
                newItem: RecyclerViewItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
