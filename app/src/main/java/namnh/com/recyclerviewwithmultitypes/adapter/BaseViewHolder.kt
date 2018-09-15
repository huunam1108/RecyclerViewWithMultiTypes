package namnh.com.recyclerviewwithmultitypes.adapter

import android.support.v7.widget.RecyclerView
import android.view.View

open class BaseViewHolder<T : RecyclerViewItem>(itemView: View) : RecyclerView.ViewHolder(
    itemView) {
    var item: T? = null
        private set

    open fun bind(item: T) {
        this.item = item
    }
}
