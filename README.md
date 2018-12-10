# RecyclerViewWithMultiTypes
My demo for multi item types using RecyclerView

## Usage

Step 1:

Add your type in `RecyclerType`
```Kotlin
const val TYPE_HEADER = 0
const val TYPE_ITEM = 1
const val TYPE_FOOTER = 2
// ... other types
```
Step 2:

Make your item implement `RecyclerViewItem` and `override` the `getType()` function

Ex:
```Kotlin
class BannerItem(...) : RecyclerViewItem {

    @RecyclerType
    override fun getType(): Int {
        return TYPE_BANNER
    }
}
```

Step 3:

Your `ViewHolder` will extend from `BaseViewHolder` and `override` the `bind()` function

```Kotlin
class BannerViewHolder(itemView: View) : BaseViewHolder<BannerItem>(itemView) {
    override fun bind(item: BannerItem) {
        super.bind(item)
        // Do your logic with view
    }
}
```

Step 4:

Your `Adapter` will extend from `BaseAdapter` and handle the [`DiffUtil.ItemCallback`](https://medium.com/@iammert/using-diffutil-in-android-recyclerview-bdca8e4fbb00) like below

```Kotlin
class DemoAdapter(data: List<RecyclerViewItem>) : BaseAdapter(data, diffUtil) {

    // Override your holder here
    override fun customViewHolder(parent: ViewGroup, viewType: Int): Any {
        return when (viewType) {
            TYPE_BANNER -> BannerViewHolder(inflateView(R.layout.item_banner, parent))
            // Other types
            else ->
                // do with none supported Type
        }
    }

    companion object {
        // Make your diffUtil working
        private val diffUtil = object : BaseDiffUtil<RecyclerViewItem>() {
            override fun areItemsTheSame(oldItem: RecyclerViewItem,
                newItem: RecyclerViewItem): Boolean {
                return true
            }

            override fun areContentsTheSame(oldItem: RecyclerViewItem,
                newItem: RecyclerViewItem): Boolean {
                // Implement to check the changed of item
                when (oldItem) {
                    is BannerItem -> {
                        return newItem is BannerItem && newItem == oldItem
                    }
                    // other types
                }
                return false
            }
        }
    }
}

```

Step 5:

Summit the list data

```Kotlin
val demoAdapter = DemoAdapter()
recyclerMain.adapter = demoAdapter
demoAdapter.submitList(yourListData)
```

That's all !
