package namnh.com.recyclerviewwithmultitypes.model.category

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import namnh.com.recyclerviewwithmultitypes.R
import namnh.com.recyclerviewwithmultitypes.adapter.BaseViewHolder

class FilterViewHolder(itemView: View) : BaseViewHolder<FilterItem>(itemView) {

    private var iconFilter: ImageView = itemView.findViewById(R.id.image_filter)
    private var tvDescription: TextView = itemView.findViewById(R.id.text_filter_description)

    override fun bind(item: FilterItem) {
        super.bind(item)
        iconFilter.setImageResource(item.image)
        tvDescription.text = item.filterName
    }
}
