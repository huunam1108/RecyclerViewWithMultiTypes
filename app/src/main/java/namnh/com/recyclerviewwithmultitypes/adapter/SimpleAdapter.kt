package namnh.com.recyclerviewwithmultitypes.adapter

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import namnh.com.recyclerviewwithmultitypes.util.removeLast

@Suppress("UNCHECKED_CAST", "UNUSED")
abstract class SimpleAdapter : RecyclerView.Adapter<BaseViewHolder<in RecyclerViewItem>>() {

    var data = mutableListOf<RecyclerViewItem>()

    fun set(item: RecyclerViewItem, index: Int) {
        data[index] = item
        notifyItemChanged(index)
    }

    fun set(items: List<RecyclerViewItem>) {
        data.apply {
            clear()
            addAll(items)
            notifyDataSetChanged()
        }
    }

    /**
     * @param forceAnim: do animation of changed rows if needed
     */
    fun set(items: List<RecyclerViewItem>, index: Int, forceAnim: Boolean = true) {
        data = data.removeLast(index).apply {
            addAll(items)
            if (forceAnim) {
                notifyItemRangeChanged(index, items.size)
            } else {
                notifyDataSetChanged()
            }
        }
    }

    fun add(item: RecyclerViewItem, index: Int) {
        data.add(index, item)
        notifyItemInserted(index)
    }

    fun add(items: List<RecyclerViewItem>) {
        val currentSize = data.size
        data.addAll(items)
        notifyItemRangeInserted(currentSize, items.size)
    }

    fun remove(position: Int) {
        if (isValidPosition(position).not()) return
        data.removeAt(position)
        notifyItemRemoved(position)
    }

    fun getItem(position: Int): RecyclerViewItem? {
        return if (isValidPosition(position)) data[position] else null
    }

    @RecyclerType
    final override fun getItemViewType(position: Int): Int {
        return getItem(position)?.getType() ?: -1
    }

    override fun getItemCount(): Int {
        return data.size
    }

    protected fun inflateView(@LayoutRes layoutRes: Int, parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
    }

    private fun isValidPosition(position: Int): Boolean {
        return data.size > position
    }

    final override fun onCreateViewHolder(parent: ViewGroup,
        viewType: Int): BaseViewHolder<RecyclerViewItem> {
        val holder = customViewHolder(parent, viewType) as? BaseViewHolder<*>
            ?: throw ClassCastException(
                "Please create BaseViewHolder for a child class of RecyclerViewItem !")
        return holder as BaseViewHolder<in RecyclerViewItem>
    }

    override fun onBindViewHolder(holder: BaseViewHolder<in RecyclerViewItem>, position: Int) {
        getItem(position)?.apply {
            holder.bind(this)
        }
    }

    abstract fun customViewHolder(parent: ViewGroup, viewType: Int): Any
}