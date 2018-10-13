package namnh.com.recyclerviewwithmultitypes.adapter

interface RecyclerViewItem {
    @RecyclerType
    fun getType(): Int

    override fun hashCode(): Int
}