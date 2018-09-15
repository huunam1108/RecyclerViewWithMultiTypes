package namnh.com.recyclerviewwithmultitypes.adapter

interface RecyclerViewItem {
    @RecyclerViewSupportedType
    fun getType(): Int
}