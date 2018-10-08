package namnh.com.recyclerviewwithmultitypes.adapter.loadmore

class Enabled(private val onEnabledListener: OnEnabledListener) {
    /**
     * Get whether loading is enabled more, the default is true
     * Set whether to enable loading more
     */
    var loadMoreEnabled = true
        set(enabled) {
            val canNotify = loadMoreEnabled
            field = enabled

            if (canNotify && !loadMoreEnabled) {
                onEnabledListener.notifyChanged()
            }
        }
    private var isLoadFailed = false
    private var isNoMore = false

    /**
     * Set whether the load failed
     *
     * @param isFailed Whether the load failed
     */
    fun setLoadFailed(isFailed: Boolean) {
        if (isLoadFailed != isFailed) {
            isLoadFailed = isFailed
            onEnabledListener.notifyLoadFailed(isFailed)
            loadMoreEnabled = !isLoadFailed
        }
    }

    fun setNoMore(noMore: Boolean) {
        if (isNoMore != noMore) {
            isNoMore = noMore
            onEnabledListener.notifyNoMore(noMore)
            loadMoreEnabled = !isNoMore
        }
    }

    interface OnEnabledListener {
        fun notifyChanged()

        fun notifyLoadFailed(isFailed: Boolean)

        fun notifyNoMore(noMore: Boolean)
    }
}