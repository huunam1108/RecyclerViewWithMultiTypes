package namnh.com.recyclerviewwithmultitypes.model.banner

import android.view.View
import namnh.com.recyclerviewwithmultitypes.R
import namnh.com.recyclerviewwithmultitypes.adapter.BaseViewHolder
import namnh.com.recyclerviewwithmultitypes.pager.CustomPagerAdapter
import namnh.com.recyclerviewwithmultitypes.pager.InfinitePagerAdapter
import namnh.com.recyclerviewwithmultitypes.pager.InfiniteViewPager
import namnh.com.recyclerviewwithmultitypes.pager.ViewPagerIndicator

class BannerViewHolder(itemView: View) : BaseViewHolder<BannerItem>(itemView) {

    private var pager: InfiniteViewPager = itemView.findViewById(R.id.pager)
    private var viewPagerIndicator: ViewPagerIndicator = itemView.findViewById(R.id.pager_indicator)
    var wrappedAdapter: InfinitePagerAdapter? = null

    init {
        viewPagerIndicator.setPager(pager)
    }

    override fun bind(item: BannerItem) {
        val adapter = CustomPagerAdapter(item.images)
        // wrap pager to provide infinite paging with wrap-around
        if (wrappedAdapter == null) {
            wrappedAdapter = InfinitePagerAdapter(adapter)
            // actually an InfiniteViewPager
            pager.adapter = wrappedAdapter
        } else {
            adapter.list = item.images
        }
    }
}
