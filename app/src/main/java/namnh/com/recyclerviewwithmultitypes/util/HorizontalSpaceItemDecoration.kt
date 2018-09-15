package namnh.com.recyclerviewwithmultitypes.util

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class HorizontalSpaceItemDecoration(
    private val horizontalSpacing: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State) {
        outRect.right = horizontalSpacing
    }
}