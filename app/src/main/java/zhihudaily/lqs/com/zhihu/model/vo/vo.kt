package zhihudaily.lqs.com.zhihu.model.vo

import android.support.v4.view.PagerAdapter
import zhihudaily.lqs.com.zhihu.adapter.IItem
import zhihudaily.lqs.com.zhihu.model.dto.Comment

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

data class NewsVo(val html: String, val title: String, val image: String, val type: Int, val id: Long)

data class CommentItemHeadVo(val avatar: String, val author: String, val likes: Int, val comment: Comment) : IItem {
    companion object {
        val TYPE = 3
    }

    override val itemType: Int = TYPE
}

data class CommentItemContentVo(val content: String, val comment: Comment) : IItem {
    companion object {
        val TYPE = 4
    }

    override val itemType: Int = TYPE
}

data class CommentTtemReplyToVo(val author: String, val content: String, val comment: Comment) : IItem {
    companion object {
        val TYPE = 5
    }

    override val itemType: Int = TYPE
}

data class CommentItemFootVo(val time: Long, val comment: Comment) : IItem {
    companion object {
        val TYPE = 6
    }

    override val itemType: Int = TYPE
}

data class CommentTitle(val title: String) : IItem {
    companion object {
        val TYPE = 7
    }

    override val itemType: Int = TYPE

}

data class ThemeVo(val name: String, val id: Long)
