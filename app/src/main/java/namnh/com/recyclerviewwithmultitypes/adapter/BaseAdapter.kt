package namnh.com.recyclerviewwithmultitypes.adapter

import android.support.annotation.LayoutRes
import android.support.v7.recyclerview.extensions.ListAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

@Suppress("UNCHECKED_CAST")
abstract class BaseAdapter protected constructor(
    diffCallback: BaseDiffUtil<*>) : ListAdapter<RecyclerViewItem, BaseViewHolder<in RecyclerViewItem>>(
    diffCallback as BaseDiffUtil<in RecyclerViewItem>) {

    abstract fun customViewHolder(parent: ViewGroup, viewType: Int): Any

    final override fun onBindViewHolder(baseViewHolder: BaseViewHolder<in RecyclerViewItem>,
        position: Int) {
        baseViewHolder.bind(getItem(position))
    }

    final override fun onCreateViewHolder(parent: ViewGroup,
        viewType: Int): BaseViewHolder<in RecyclerViewItem> {
        val holder = customViewHolder(parent, viewType) as? BaseViewHolder<*>
            ?: throw ClassCastException(
                "Please create BaseViewHolder of a child class of RecyclerViewItem !")
        return holder as BaseViewHolder<in RecyclerViewItem>
    }

    @RecyclerType
    final override fun getItemViewType(position: Int): Int {
        return getItem(position).getType()
    }

    final override fun getItemId(position: Int): Long {
        return getItem(position).hashCode().toLong()
    }

    protected fun inflateView(@LayoutRes layoutRes: Int, parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
    }

    final override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(true)
    }
}
