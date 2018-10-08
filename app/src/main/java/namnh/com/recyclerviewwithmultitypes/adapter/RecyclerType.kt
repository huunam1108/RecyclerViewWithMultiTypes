package namnh.com.recyclerviewwithmultitypes.adapter

import android.support.annotation.IntDef

@IntDef(TYPE_UNKNOWN, TYPE_HEADER, TYPE_ITEM, TYPE_FOOTER, TYPE_VIDEO_ITEM, TYPE_GRID_VIDEO,
    TYPE_BANNER, TYPE_FILTER_ITEM, TYPE_LIST_FILTER, TYPE_NO_MORE, TYPE_LOAD_FAILED)
@Retention(AnnotationRetention.SOURCE)
annotation class RecyclerType

const val TYPE_UNKNOWN = -1
const val TYPE_HEADER = 0
const val TYPE_ITEM = 1
const val TYPE_FOOTER = 2
const val TYPE_VIDEO_ITEM = 3
const val TYPE_GRID_VIDEO = 4
const val TYPE_BANNER = 5
const val TYPE_FILTER_ITEM = 6
const val TYPE_LIST_FILTER = 7
const val TYPE_NO_MORE = 8
const val TYPE_LOAD_FAILED = 9
