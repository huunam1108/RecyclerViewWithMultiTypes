package namnh.com.recyclerviewwithmultitypes.adapter

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseViewHolder<T : RecyclerViewItem>(itemView: View) : RecyclerView.ViewHolder(
    itemView) {
    abstract fun bind(item: T)
}
