package namnh.com.recyclerviewwithmultitypes.adapter.loadmore

import android.support.annotation.LayoutRes
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import namnh.com.recyclerviewwithmultitypes.R

class LoadMoreAdapter internal constructor(
    adapter: RecyclerView.Adapter<*>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var progressView: View? = null
    private var progressResId = View.NO_ID

    var infoView: View? = null
    private var infoResId = View.NO_ID

    var retryView: View? = null
    private var retryResId = View.NO_ID

    var retryMessage: String? = null
    var infoMessage: String? = null

    lateinit var originalAdapter: RecyclerView.Adapter<*>
    private var recyclerView: RecyclerView? = null
    private var onLoadMoreListener: OnLoadMoreListener? = null

    private lateinit var enabled: Enabled
    private var isLoading: Boolean = false
    private var shouldRemove: Boolean = false
    private var showInfoEnabled: Boolean = false
    private var isLoadFailed: Boolean = false
    private var isNoMore: Boolean = false


    /**
     * Deciding whether to trigger loading
     */
    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if (!loadMoreEnabled || isLoading || onLoadMoreListener == null) {
                return
            }

            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                val layoutManager = recyclerView?.layoutManager
                val isBottom = when (layoutManager) {
                    is LinearLayoutManager -> layoutManager.findLastVisibleItemPosition() >= layoutManager.getItemCount() - 1
                    is StaggeredGridLayoutManager -> {
                        val into = IntArray(layoutManager.spanCount)
                        layoutManager.findLastVisibleItemPositions(into)

                        last(into) >= layoutManager.getItemCount() - 1
                    }
                    else -> (layoutManager as GridLayoutManager).findLastVisibleItemPosition() >= layoutManager.getItemCount() - 1
                }

                if (isBottom) {
                    isLoading = true
                    onLoadMoreListener!!.onLoadMore(enabled)
                }
            }
        }
    }

    var loadMoreEnabled: Boolean
        get() = enabled.loadMoreEnabled && originalAdapter.itemCount >= 0
        set(enabled) {
            this.enabled.loadMoreEnabled = enabled
        }

    private val onEnabledListener = object : Enabled.OnEnabledListener {
        override fun notifyChanged() {
            shouldRemove = true
        }

        override fun notifyLoadFailed(isFailed: Boolean) {
            isLoadFailed = isFailed
            notifyFooterHolderChanged()
        }

        override fun notifyNoMore(noMore: Boolean) {
            isNoMore = noMore
            notifyFooterHolderChanged()
        }
    }

    private val dataObserver: RecyclerView.AdapterDataObserver = object : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            if (shouldRemove) {
                shouldRemove = false
            }
            this@LoadMoreAdapter.notifyDataSetChanged()
            isLoading = false
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
            if (shouldRemove && positionStart == originalAdapter.itemCount) {
                shouldRemove = false
            }
            // UnComment this if you want more animations
            // this@LoadMoreAdapter.notifyItemRangeChanged(positionStart, itemCount)
            isLoading = false
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
            if (shouldRemove && positionStart == originalAdapter.itemCount) {
                shouldRemove = false
            }
            // UnComment this if you want more animations
            // this@LoadMoreAdapter.notifyItemRangeChanged(positionStart, itemCount, payload)
            isLoading = false
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            // when no data is initialized (has loadMoreView)
            // should remove loadMoreView before notifyItemRangeInserted
            if (recyclerView?.childCount == 1) {
                this@LoadMoreAdapter.notifyItemRemoved(0)
            }
            // UnComment this if you want more animations
            // this@LoadMoreAdapter.notifyItemRangeInserted(positionStart, itemCount)
            notifyFooterHolderChanged()
            isLoading = false
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            if (shouldRemove && positionStart == originalAdapter.itemCount) {
                shouldRemove = false
            }
            /*
               use notifyItemRangeRemoved after clear item, can throw IndexOutOfBoundsException
               @link RecyclerView#tryGetViewHolderForPositionByDeadline
               fix java.lang.IndexOutOfBoundsException: Inconsistency detected. Invalid item position
             */
            var shouldSync = false
            if (enabled.loadMoreEnabled && originalAdapter.itemCount == 0) {
                loadMoreEnabled = false
                shouldSync = true
                // when use onItemRangeInserted(0, count) after clear item
                // recyclerView will auto scroll to bottom, because has one item(loadMoreView)
                // remove loadMoreView
                if (getItemCount() == 1) {
                    this@LoadMoreAdapter.notifyItemRemoved(0)
                }
            }
            // UnComment this if you want more animations
            // this@LoadMoreAdapter.notifyItemRangeRemoved(positionStart, itemCount)
            if (shouldSync) {
                loadMoreEnabled = true
            }
            isLoading = false
        }

        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
            if (shouldRemove && (fromPosition == originalAdapter.itemCount || toPosition == originalAdapter.itemCount)) {
                throw IllegalArgumentException(
                    "can not move last position after setLoadMoreEnabled(false)")
            }
            // UnComment this if you want more animations
            // this@LoadMoreAdapter.notifyItemMoved(fromPosition, toPosition)
            isLoading = false
        }
    }

    init {
        registerAdapter(adapter)
    }

    private fun registerAdapter(adapter: RecyclerView.Adapter<*>?) {
        if (adapter == null) {
            throw NullPointerException("adapter can not be null!")
        }

        originalAdapter = adapter
        originalAdapter.registerAdapterDataObserver(dataObserver)
        enabled = Enabled(onEnabledListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_PROGRESS -> {
                if (progressResId != View.NO_ID) {
                    progressView = LoadMoreHelper.inflate(parent, progressResId)
                }
                if (progressView != null) {
                    return ProgressHolder(progressView!!)
                }
                return ProgressHolder(LoadMoreHelper.inflate(parent, R.layout.layout_progress))
            }
            TYPE_INFO -> {
                if (infoResId != View.NO_ID) {
                    infoView = LoadMoreHelper.inflate(parent, infoResId)
                }
                if (infoView != null) {
                    return InfoHolder(infoView!!)
                }
                return InfoHolder(LoadMoreHelper.inflate(parent, R.layout.layout_info))
            }
            TYPE_RETRY -> {
                if (retryResId != View.NO_ID) {
                    retryView = LoadMoreHelper.inflate(parent, retryResId)
                }
                var view = retryView
                if (view == null) {
                    view = LoadMoreHelper.inflate(parent, R.layout.layout_retry)
                }
                return RetryHolder(view, enabled, onLoadMoreListener)
            }
            else -> return originalAdapter.onCreateViewHolder(parent, viewType)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {}

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int,
        payloads: List<Any>) {
        when (holder) {
            is ProgressHolder -> {
                if (!canScroll(
                        recyclerView?.layoutManager?.layoutDirection ?: RecyclerView.VERTICAL)
                    && onLoadMoreListener != null
                    && !isLoading) {

                    isLoading = true
                    // fix Cannot call this method while RecyclerView is computing a layout or scrolling
                    recyclerView?.post { onLoadMoreListener!!.onLoadMore(enabled) }
                }
            }
            is InfoHolder -> {
                holder.bind(infoMessage)
            }
            is RetryHolder -> {
                holder.bind(retryMessage)
            }
            else -> {
                @Suppress("UNCHECKED_CAST")
                (originalAdapter as RecyclerView.Adapter<RecyclerView.ViewHolder>).onBindViewHolder(
                    holder, position, payloads)
            }
        }
    }

    override fun getItemCount(): Int {
        val count = originalAdapter.itemCount
        return when {
            loadMoreEnabled -> count + 1
            showInfoEnabled -> count + 1
            else -> count + if (shouldRemove) 1 else 0
        }
    }

    override fun getItemViewType(position: Int): Int {
        val isLastItem = position == originalAdapter.itemCount
        if (isLastItem && isLoadFailed) {
            return TYPE_RETRY
        }
        if (isLastItem && (loadMoreEnabled || shouldRemove)) {
            return TYPE_PROGRESS
        } else if (isLastItem && showInfoEnabled && !loadMoreEnabled) {
            return TYPE_INFO
        }
        return originalAdapter.getItemViewType(position)
    }

    private fun canScroll(layoutDirection: Int): Boolean {
        if (recyclerView == null) {
            throw NullPointerException(
                "recyclerView is null, you should setAdapter(recyclerAdapter);")
        }
        return recyclerView?.canScrollVertically(layoutDirection) ?: false
    }

    fun setFooterView(@LayoutRes resId: Int) {
        progressResId = resId
    }

    fun setNoMoreView(@LayoutRes resId: Int) {
        infoResId = resId
    }

    fun setLoadFailedView(@LayoutRes resId: Int) {
        retryResId = resId
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
        recyclerView.addOnScrollListener(onScrollListener)
        val layoutManager = recyclerView.layoutManager
        if (layoutManager is GridLayoutManager) {
            val originalSizeLookup = layoutManager.spanSizeLookup
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    val itemViewType = getItemViewType(position)
                    if (itemViewType == TYPE_PROGRESS
                        || itemViewType == TYPE_INFO
                        || itemViewType == TYPE_RETRY) {
                        return layoutManager.spanCount
                    } else if (originalSizeLookup != null) {
                        return originalSizeLookup.getSpanSize(position)
                    }

                    return 1
                }
            }
        }
    }

    private fun last(lastPositions: IntArray): Int {
        var last = lastPositions[0]
        for (value in lastPositions) {
            if (value > last) {
                last = value
            }
        }
        return last
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        recyclerView.removeOnScrollListener(onScrollListener)
        originalAdapter.unregisterAdapterDataObserver(dataObserver)
        this.recyclerView = null
    }

    fun setLoadMoreListener(listener: OnLoadMoreListener) {
        onLoadMoreListener = listener
    }

    fun setShowNoMoreEnabled(showNoMoreEnabled: Boolean) {
        showInfoEnabled = showNoMoreEnabled
    }

    private fun notifyFooterHolderChanged() {
        if (loadMoreEnabled) {
            this@LoadMoreAdapter.notifyItemChanged(originalAdapter.itemCount)
        } else if (shouldRemove) {
            shouldRemove = false
            /*
              fix IndexOutOfBoundsException when setLoadMoreEnabled(false) and then use onItemRangeInserted
              @see android.support.v7.widget.RecyclerView.Recycler#validateViewHolderForOffsetPosition(RecyclerView.ViewHolder)
             */
            val position = originalAdapter.itemCount
            val viewHolder = recyclerView?.findViewHolderForAdapterPosition(position)
            if (viewHolder is ProgressHolder) {
                this@LoadMoreAdapter.notifyItemRemoved(position)
            } else {
                this@LoadMoreAdapter.notifyItemChanged(position)
            }
        }
    }

    class ProgressHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            LoadMoreHelper.setItemViewFullSpan(itemView)
        }
    }

    class InfoHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textInfo = itemView.findViewById<TextView>(R.id.footer_info_message)

        init {
            LoadMoreHelper.setItemViewFullSpan(itemView)
        }

        fun bind(text: String?) {
            if (textInfo != null && !text.isNullOrEmpty()) {
                textInfo.text = text
            }
        }
    }

    class RetryHolder internal constructor(itemView: View, val enabled: Enabled,
        private val listener: OnLoadMoreListener?) : RecyclerView.ViewHolder(itemView) {

        private val btnRetry = itemView.findViewById<View>(R.id.footer_retry_button)
        private val textRetry = itemView.findViewById<TextView>(R.id.footer_retry_message)

        init {
            LoadMoreHelper.setItemViewFullSpan(itemView)
            if (btnRetry != null) {
                btnRetry.setOnClickListener {
                    reload()
                }
            } else {
                itemView.setOnClickListener {
                    reload()
                }
            }
        }

        fun bind(text: String?) {
            if (textRetry != null && !text.isNullOrEmpty()) {
                textRetry.text = text
            }
        }

        private fun reload() {
            enabled.setLoadFailed(false)
            listener?.onLoadMore(enabled)
        }
    }

    interface OnLoadMoreListener {
        fun onLoadMore(enabled: Enabled?)
    }

    companion object {
        // DON'T make your recycler view type like below
        const val TYPE_PROGRESS = 99997
        const val TYPE_INFO = 99998
        const val TYPE_RETRY = 99999
    }
}
