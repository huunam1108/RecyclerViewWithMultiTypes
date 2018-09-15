package namnh.com.recyclerviewwithmultitypes.model.video

import android.view.View
import android.widget.TextView
import namnh.com.recyclerviewwithmultitypes.R
import namnh.com.recyclerviewwithmultitypes.adapter.BaseViewHolder

class VideoDetailViewHolder(itemView: View) : BaseViewHolder<VideoDetailItem>(itemView) {

    private var imagePreview: View = itemView.findViewById(R.id.image_thumb)
    private var tvDescription: TextView = itemView.findViewById(R.id.text_description)
    private var tvUploader: TextView = itemView.findViewById(R.id.text_uploader)
    private var tvVideoViewed: TextView = itemView.findViewById(R.id.text_video_viewed)

    override fun bind(item: VideoDetailItem) {
        super.bind(item)
        imagePreview.setBackgroundColor(item.image)
        tvDescription.text = item.videoName
        tvUploader.text = item.uploader
        tvVideoViewed.text = itemView.context.getString(R.string.video_viewed_number,
            item.videoViewedNumber)
    }
}
