package namnh.com.recyclerviewwithmultitypes.pager

import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import namnh.com.recyclerviewwithmultitypes.R

class CustomPagerAdapter constructor(var list: List<PagerColorItem>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val rootView = LayoutInflater.from(container.context)
            .inflate(R.layout.layout_pager, container, false)
        val holder = ViewHolder(rootView)
        val data = list[position]
        holder.pagerImage.setBackgroundColor(data.image)
        container.addView(rootView)
        return rootView
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, item: Any): Boolean {
        return view === item
    }

    override fun destroyItem(container: ViewGroup, position: Int, item: Any) {
        container.removeView(item as View)
    }

    internal class ViewHolder(rootView: View) {
        var pagerImage: ImageView = rootView.findViewById(R.id.img_banner)
    }
}
