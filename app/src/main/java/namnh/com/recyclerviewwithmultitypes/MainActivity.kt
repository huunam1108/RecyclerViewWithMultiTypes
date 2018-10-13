package namnh.com.recyclerviewwithmultitypes

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
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

    private lateinit var demoAdapter: DemoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerMain = findViewById<RecyclerView>(R.id.main_recycler)
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        layoutManager.initialPrefetchItemCount = 4
        recyclerMain.layoutManager = layoutManager
        recyclerMain.addItemDecoration(VerticalSpaceItemDecoration(24))
        recyclerMain.isNestedScrollingEnabled = true

        demoAdapter = DemoAdapter()
        recyclerMain.adapter = demoAdapter

        demoAdapter.submitList(initData())
    }

    private fun initData(): List<RecyclerViewItem> {
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
            FilterItem(R.drawable.ic_launcher_background, "Music"),
            FilterItem(R.drawable.ic_launcher_background, "Movie"),
            FilterItem(R.drawable.ic_launcher_background, "Entertainment"),
            FilterItem(R.drawable.ic_launcher_background, "Cartoon"),
            FilterItem(R.drawable.ic_launcher_background, "Children"),
            FilterItem(R.drawable.ic_launcher_background, "Bolero"),
            FilterItem(R.drawable.ic_launcher_background, "OldMusic"),
            FilterItem(R.drawable.ic_launcher_background, "Music"),
            FilterItem(R.drawable.ic_launcher_background, "Movie"),
            FilterItem(R.drawable.ic_launcher_background, "Entertainment"),
            FilterItem(R.drawable.ic_launcher_background, "Cartoon"),
            FilterItem(R.drawable.ic_launcher_background, "Children"),
            FilterItem(R.drawable.ic_launcher_background, "Bolero"),
            FilterItem(R.drawable.ic_launcher_background, "OldMusic"),
            FilterItem(R.drawable.ic_launcher_background, "Other")
        )
        tempData.add(FilterCategoryItem(filter))

        // temp data for video
        val category = arrayListOf("You might like this", "Watch Later", "Trending", "Live Now")
        val videos = arrayListOf<VideoDetailItem>()
        for (i in 0..3) {
            videos.add(VideoDetailItem(Color.RED, "You may like this video $i",
                "CNBC Channel", random()))
        }
        for (i in 0..20) {
            tempData.add(GridVideoDetailItem(
                R.drawable.ic_music_video, category.random(), videos))
        }
        return tempData
    }

    private val random = Random()
    private fun random(): Int = random.nextInt(200000)
    private fun <E> List<E>.random(): E? = if (size > 0) get(random.nextInt(size)) else null
}
