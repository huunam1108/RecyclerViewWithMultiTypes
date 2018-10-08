package namnh.com.recyclerviewwithmultitypes.adapter.loadmore

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View

@Suppress("unused")
class LoadMoreWrapper private constructor(private val loadMoreAdapter: LoadMoreAdapter) {

    val footerView: View?
        get() = loadMoreAdapter.progressView

    val noMoreView: View?
        get() = loadMoreAdapter.infoView

    val loadFailedView: View?
        get() = loadMoreAdapter.retryView

    val originalAdapter: RecyclerView.Adapter<*>
        get() = loadMoreAdapter.originalAdapter

    fun setFooterView(@LayoutRes resId: Int): LoadMoreWrapper {
        loadMoreAdapter.setFooterView(resId)
        return this
    }

    fun setFooterView(footerView: View): LoadMoreWrapper {
        loadMoreAdapter.progressView = footerView
        return this
    }

    fun setNoMoreView(@LayoutRes resId: Int): LoadMoreWrapper {
        loadMoreAdapter.setNoMoreView(resId)
        return this
    }

    fun setNoMoreView(noMoreView: View): LoadMoreWrapper {
        loadMoreAdapter.infoView = noMoreView
        return this
    }

    fun setLoadFailedView(@LayoutRes resId: Int): LoadMoreWrapper {
        loadMoreAdapter.setLoadFailedView(resId)
        return this
    }

    fun setLoadFailedView(view: View): LoadMoreWrapper {
        loadMoreAdapter.retryView = view
        return this
    }

    fun setLoadMoreListener(listener: LoadMoreAdapter.OnLoadMoreListener): LoadMoreWrapper {
        loadMoreAdapter.setLoadMoreListener(listener)
        return this
    }

    fun setShowNoMoreEnabled(enabled: Boolean): LoadMoreWrapper {
        loadMoreAdapter.setShowNoMoreEnabled(enabled)
        return this
    }

    fun into(recyclerView: RecyclerView): LoadMoreAdapter {
        recyclerView.adapter = loadMoreAdapter
        return loadMoreAdapter
    }

    companion object {
        fun with(adapter: RecyclerView.Adapter<*>): LoadMoreWrapper {
            return LoadMoreWrapper(LoadMoreAdapter(adapter))
        }
    }
}
