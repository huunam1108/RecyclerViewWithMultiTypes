package namnh.com.recyclerviewwithmultitypes.adapter

import android.support.annotation.LayoutRes
import android.support.v7.recyclerview.extensions.ListAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseAdapter protected constructor(val data: List<RecyclerViewItem>,
    diffCallback: BaseDiffUtil<in RecyclerViewItem>) : ListAdapter<RecyclerViewItem, BaseViewHolder<in RecyclerViewItem>>(
    diffCallback) {

    final override fun onBindViewHolder(baseViewHolder: BaseViewHolder<in RecyclerViewItem>,
        position: Int) {
        baseViewHolder.bind(getItem(position))
    }

    @RecyclerViewSupportedType
    final override fun getItemViewType(position: Int): Int {
        return getItem(position).getType()
    }

    final override fun getItem(position: Int): RecyclerViewItem {
        return data[position]
    }

    final override fun getItemCount(): Int {
        return data.size
    }

    final override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    protected fun inflateView(@LayoutRes layoutRes: Int, parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
    }
}
