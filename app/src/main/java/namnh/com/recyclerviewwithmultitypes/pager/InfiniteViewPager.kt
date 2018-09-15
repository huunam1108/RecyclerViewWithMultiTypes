package namnh.com.recyclerviewwithmultitypes.pager

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import namnh.com.recyclerviewwithmultitypes.R

private const val DEFAULT_AUTO_SCROLL_INTERVAL = 2000

class InfiniteViewPager : ViewPager {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    private var autoScroll: Boolean = false
    private var autoSmoothScroll: Boolean = false
    private var autoScrollInterval: Long = 0
    private val autoScrollHandler = Handler()
    private var hasAlreadyPosted: Boolean = false
    private var lastDiff: Int = 0
    // allow for 100 back cycles from the beginning
    // should be enough to create an illusion of infinity
    // warning: scrolling to very high values (1,000,000+) results in
    // strange drawing behaviour
    private val offsetAmount: Int
        get() {
            return when {
                adapter?.count == 0 -> 0
                adapter is InfinitePagerAdapter -> {
                    val infAdapter = adapter as InfinitePagerAdapter
                    infAdapter.realCount * 100
                }
                else -> 0
            }
        }

    private val runnable = object : Runnable {
        override fun run() {
            setCurrentItem(currentItem + 1, !autoScroll)
            autoScrollHandler.postDelayed(this, autoScrollInterval)
        }
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if (visibility == View.VISIBLE) {
            checkAndResumeAutoScroll()
        } else {
            checkAndStopAutoScroll()
        }
    }

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
        if (hasWindowFocus) {
            checkAndResumeAutoScroll()
        } else {
            checkAndStopAutoScroll()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> checkAndStopAutoScroll()
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> checkAndResumeAutoScroll()
        }
        return super.onTouchEvent(ev)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        checkAndResumeAutoScroll()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        checkAndStopAutoScroll()
    }

    private fun init(attrs: AttributeSet?) {
        attrs?.let {
            val a = context.obtainStyledAttributes(it, R.styleable.InfiniteViewPager)
            autoScroll = a.getBoolean(R.styleable.InfiniteViewPager_autoScroll, false)
            autoSmoothScroll = a.getBoolean(R.styleable.InfiniteViewPager_autoSmoothScroll, false)
            autoScrollInterval = a.getInt(R.styleable.InfiniteViewPager_autoScrollInterval,
                DEFAULT_AUTO_SCROLL_INTERVAL).toLong()
            a.recycle()
        }
        addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float,
                positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                lastDiff = position - offsetAmount
            }
        })
    }

    private fun checkAndResumeAutoScroll() {
        if (!autoScroll && !autoSmoothScroll || hasAlreadyPosted) return
        autoScrollHandler.postDelayed(runnable, autoScrollInterval)
        hasAlreadyPosted = true

    }

    private fun checkAndStopAutoScroll() {
        if (!autoScroll && !autoSmoothScroll || !hasAlreadyPosted) return
        autoScrollHandler.removeCallbacksAndMessages(null)
        hasAlreadyPosted = false
    }

    override fun setAdapter(adapter: PagerAdapter?) {
        super.setAdapter(adapter)
        // offset first element so that we can scroll to the left
        currentItem = 0
    }

    override fun setCurrentItem(position: Int) {
        // offset the current position to ensure there is space to scroll
        setCurrentItem(position, false)
    }

    override fun setCurrentItem(position: Int, smoothScroll: Boolean) {
        if (adapter == null) return
        var tempPos = position
        if (adapter!!.count == 0) {
            super.setCurrentItem(tempPos, smoothScroll)
            return
        }
        tempPos = if (lastDiff > position) {
            offsetAmount + lastDiff + 1
        } else
            offsetAmount + position % adapter!!.count
        lastDiff = tempPos - offsetAmount
        super.setCurrentItem(tempPos, smoothScroll)
    }

    override fun getCurrentItem(): Int {
        if (adapter?.count == 0) {
            return super.getCurrentItem()
        }
        val position = super.getCurrentItem()
        return if (adapter is InfinitePagerAdapter) {
            val infAdapter = adapter as InfinitePagerAdapter
            // Return the actual item position in the data backing InfinitePagerAdapter
            position % infAdapter.realCount
        } else {
            super.getCurrentItem()
        }
    }
}
