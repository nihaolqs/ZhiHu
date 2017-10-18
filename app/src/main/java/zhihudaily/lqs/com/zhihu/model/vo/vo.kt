package zhihudaily.lqs.com.zhihu.model.vo

import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import zhihudaily.lqs.com.zhihu.adapter.IItem
import kotlin.properties.Delegates

/**
 * Created by admin on 2017/8/31.
 */
data class StoryVo(val images: String, val type: Int, val id: Long, val ga_prefix: String, val title: String) : IItem {
    companion object {
        val TYPE = 1
    }

    override val itemType: Int = TYPE
}

data class TopStorysVo(val storyList: List<StoryVo>) : IItem {
    companion object {
        val TYPE = 2
    }

    override val itemType: Int = TYPE

    var adatpter: PagerAdapter? = null
}

data class NewsVo(val html: String,  val title: String, val image: String, val type: Int, val id: Long)