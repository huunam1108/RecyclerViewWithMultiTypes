package namnh.com.recyclerviewwithmultitypes.adapter.loadmore

import android.support.annotation.LayoutRes
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

internal object LoadMoreHelper {

    fun inflate(parent: ViewGroup, @LayoutRes resource: Int): View {
        return LayoutInflater.from(parent.context).inflate(resource, parent, false)
    }

    fun setItemViewFullSpan(itemView: View) {
        val layoutParams = itemView.layoutParams
        if (layoutParams is StaggeredGridLayoutManager.LayoutParams) {
            layoutParams.isFullSpan = true
        }
    }
}
