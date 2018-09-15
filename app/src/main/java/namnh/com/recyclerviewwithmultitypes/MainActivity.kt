package namnh.com.recyclerviewwithmultitypes

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import namnh.com.recyclerviewwithmultitypes.adapter.RecyclerViewItem
import namnh.com.recyclerviewwithmultitypes.model.banner.BannerItem
import namnh.com.recyclerviewwithmultitypes.model.category.FilterCategoryItem
import namnh.com.recyclerviewwithmultitypes.model.category.FilterItem
import namnh.com.recyclerviewwithmultitypes.model.video.GridVideoDetailItem
import namnh.com.recyclerviewwithmultitypes.model.video.VideoDetailItem
import namnh.com.recyclerviewwithmultitypes.pager.PagerColorItem
import namnh.com.recyclerviewwithmultitypes.util.VerticalSpaceItemDecoration
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerMain = findViewById<RecyclerView>(R.id.main_recycler)
        recyclerMain.addItemDecoration(VerticalSpaceItemDecoration(24))
        val tempData = arrayListOf<RecyclerViewItem>()

        // temp data for banner
        val colors = listOf(
            PagerColorItem(Color.CYAN),
            PagerColorItem(Color.MAGENTA),
            PagerColorItem(Color.GREEN),
            PagerColorItem(Color.YELLOW))
        tempData.add(BannerItem(colors))

        // temp data for filter
        val filter = arrayListOf(
            FilterItem(R.drawable.ic_launcher_background, "Ca nhạc"),
            FilterItem(R.drawable.ic_launcher_background, "Phim"),
            FilterItem(R.drawable.ic_launcher_background, "Giải trí"),
            FilterItem(R.drawable.ic_launcher_background, "Hoạt hình"),
            FilterItem(R.drawable.ic_launcher_background, "Thiếu nhi"),
            FilterItem(R.drawable.ic_launcher_background, "Bolero"),
            FilterItem(R.drawable.ic_launcher_background, "Phim Hàn"),
            FilterItem(R.drawable.ic_launcher_background, "Cổ nhạc"),
            FilterItem(R.drawable.ic_launcher_background, "Khác")
        )
        tempData.add(FilterCategoryItem(filter))

        // temp data for video
        val category = arrayListOf("Có thể bạn thích xem", "Xem sau", "Xem nhiều",
            "Đang trực tuyến")
        val videos = arrayListOf<VideoDetailItem>()
        for (i in 0..3) {
            videos.add(VideoDetailItem(Color.RED,
                "Đây là video bạn muốn xem $i",
                "CNBC Channel", random()))
        }
        for (i in 0..20) {
            tempData.add(GridVideoDetailItem(
                R.drawable.ic_music_video, category.random(), videos))
        }


        recyclerMain.isNestedScrollingEnabled = false
        recyclerMain.setHasFixedSize(true)
        recyclerMain.adapter = DemoAdapter(tempData)
    }

    private fun random(): Int = Random().nextInt(200000)
    private fun <E> List<E>.random(): E? = if (size > 0) get(Random().nextInt(size)) else null
}

