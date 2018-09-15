package namnh.com.recyclerviewwithmultitypes.pager

import android.content.Context
import android.database.DataSetObserver
import android.graphics.Color
import android.graphics.LightingColorFilter
import android.support.annotation.*
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import namnh.com.recyclerviewwithmultitypes.R

class ViewPagerIndicator : LinearLayout, ViewPager.OnPageChangeListener, ViewPager.OnAdapterChangeListener {

    constructor(context: Context) : super(context) {
        initializeViews(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initializeViews(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs,
        defStyleAttr) {
        initializeViews(context, attrs)
    }

    private var viewPager: ViewPager? = null

    @ColorInt
    private var selectedColor = -1
    @ColorInt
    private var normalColor = -1
    @DrawableRes
    private var selectedDrawable = -1
    @DrawableRes
    private var normalDrawable = -1
    @Dimension
    private var indicatorSpacing = 5
    @ColorInt
    private var backgroundColorCustom = -1
    private var animationDuration = 150
    private var animScaleMultiplier = 1.5f
    private var isAnimate = false

    private val dataSetObserver = object : DataSetObserver() {
        override fun onChanged() {
            super.onChanged()

            if (viewPager?.adapter is InfinitePagerAdapter) {
                initializeIndicatorBar((viewPager?.adapter as InfinitePagerAdapter).realCount)
            } else {
                initializeIndicatorBar(viewPager?.adapter?.count ?: 0)
            }
        }
    }

    /**
     * Set this after setting the adapter to the pager.
     * @param pager the connected viewpager
     */
    fun setPager(@NonNull pager: ViewPager) {

        viewPager?.let {
            it.removeOnPageChangeListener(this@ViewPagerIndicator)
            it.removeOnAdapterChangeListener(this@ViewPagerIndicator)
            it.adapter?.unregisterDataSetObserver(dataSetObserver)
        }

        viewPager = pager
        viewPager?.let {
            if (it.adapter is InfinitePagerAdapter) {
                initializeIndicatorBar((it.adapter as InfinitePagerAdapter).realCount)
            } else {
                initializeIndicatorBar(it.adapter?.count ?: 0)
            }
            it.addOnPageChangeListener(this)
            it.addOnAdapterChangeListener(this)
            it.adapter?.registerDataSetObserver(dataSetObserver)
        }

    }

    private fun initializeViews(context: Context, attrs: AttributeSet?) {
        attrs?.let {
            val a = context.obtainStyledAttributes(it, R.styleable.ViewPagerIndicator)
            selectedColor = a.getColor(R.styleable.ViewPagerIndicator_selectedColor,
                getThemeColor(R.attr.colorAccent))
            normalColor = a.getColor(R.styleable.ViewPagerIndicator_deselectedColor,
                Color.LTGRAY)
            selectedDrawable = a.getResourceId(R.styleable.ViewPagerIndicator_selectedDrawable, -1)
            normalDrawable = a.getResourceId(R.styleable.ViewPagerIndicator_deselectedDrawable,
                -1)
            indicatorSpacing = a.getDimension(R.styleable.ViewPagerIndicator_indicatorSpacing,
                5f).toInt()
            isAnimate = a.getBoolean(R.styleable.ViewPagerIndicator_enableAnimation, false)
            animationDuration = a.getInteger(R.styleable.ViewPagerIndicator_animationDuration, 150)
            animScaleMultiplier = a.getFloat(R.styleable.ViewPagerIndicator_animationScale, 1.5f)
            backgroundColorCustom = a.getColor(R.styleable.ViewPagerIndicator_backgroundColor,
                Color.TRANSPARENT)
            a.recycle()
        }

        setBackgroundColor(backgroundColorCustom)
    }

    @ColorInt
    private fun getThemeColor(@AttrRes attributeColor: Int): Int {
        val value = TypedValue()
        context.theme.resolveAttribute(attributeColor, value, true)
        return value.data
    }

    private fun initializeIndicatorBar(num: Int) {
        removeAllViewsInLayout()

        for (i in 0 until num) {
            val img = ImageView(context)
            img.tag = i

            val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
            lp.setMargins(indicatorSpacing / 2, 0, indicatorSpacing / 2, 0)
            lp.gravity = Gravity.CENTER_VERTICAL or Gravity.CENTER_HORIZONTAL
            addView(img, lp)
        }

        setSelectedIndicator(viewPager?.currentItem ?: 0)
    }

    private fun setSelectedIndicator(selected: Int) {
        val num = childCount

        for (i in 0 until num) {
            val img = getChildAt(i) as ImageView?

            img?.let {
                if (isAnimate) {
                    it.clearAnimation()

                    it.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(animationDuration.toLong())
                        .start()
                }

                it.clearColorFilter()

                when {
                    normalDrawable != -1 -> it.setImageResource(normalDrawable)
                    selectedDrawable != -1 -> {
                        it.setImageResource(selectedDrawable)
                        it.colorFilter = LightingColorFilter(0, normalColor)
                    }
                    else -> {
                        it.setImageResource(R.drawable.circle_drawable)
                        it.colorFilter = LightingColorFilter(0, normalColor)
                    }
                }
            }
        }

        val selectedView = getChildAt(selected) as ImageView?
        selectedView?.let {
            if (isAnimate) {
                it.animate()
                    .scaleX(animScaleMultiplier)
                    .scaleY(animScaleMultiplier)
                    .setDuration(animationDuration.toLong())
                    .start()
            }

            if (selectedDrawable != -1) {
                it.clearColorFilter()
                it.setImageResource(selectedDrawable)
            } else {
                it.colorFilter = LightingColorFilter(0, selectedColor)
            }
        }
    }


    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageSelected(position: Int) {
        if (viewPager?.adapter is InfinitePagerAdapter) {
            val realPos = position % (viewPager?.adapter as InfinitePagerAdapter).realCount
            setSelectedIndicator(realPos)
        } else {
            setSelectedIndicator(position)
        }
    }

    override fun onAdapterChanged(viewPager: ViewPager, oldAdapter: PagerAdapter?,
        newAdapter: PagerAdapter?) {
        oldAdapter?.unregisterDataSetObserver(dataSetObserver)
        newAdapter?.let {
            if (it is InfinitePagerAdapter) {
                initializeIndicatorBar(it.realCount)
            } else {
                initializeIndicatorBar(it.count)
            }
            it.registerDataSetObserver(dataSetObserver)
        }
    }
}
