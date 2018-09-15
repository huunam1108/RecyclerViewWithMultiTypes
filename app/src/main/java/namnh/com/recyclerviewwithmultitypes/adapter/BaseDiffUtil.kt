package namnh.com.recyclerviewwithmultitypes.adapter

import android.support.v7.util.DiffUtil

abstract class BaseDiffUtil<T: RecyclerViewItem>: DiffUtil.ItemCallback<T>()
