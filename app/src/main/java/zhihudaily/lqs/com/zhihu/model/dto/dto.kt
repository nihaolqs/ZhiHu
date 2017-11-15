package zhihudaily.lqs.com.zhihu.model.dto

/**
 * Created by admin on 2017/8/29.
 */

/**
 * 新闻元数据
 */
data class Story(val images: List<String>, val type: Int, val id: Long, val ga_prefix: String, val title: String)

/**
 * Banner元数据
 */
data class TopStory(val image: String, val type: Int, val id: Long, val ga_prefix: String, val title: String)

/**
编辑元数据
 */
data class Editor(val url: String, val bio: String, val id: Int, val avatar: String, val name: String)

/**
 * 日报
 */
data class Daily(val date: Long, val stories: List<Story>, val top_stories: List<TopStory>?)

/**
 * 频道
 */
data class Theme(val color: Int, val thumbnail: String, val description: String, val id: Long, val name: String)

/**
 *  频道列表
 */

data class Themes(val limit: Int, val subscribed: List<Theme>, val others: List<Theme>)

/**
 * 频道新闻
 */
data class ThemeDetail(val description: String, val background: String, val color: Int, val name: String,
                       val image: String, val image_source: String, val stories: List<Story>, val editors: List<Editor>)

data class News(val body: String, val image_source: String, val title: String, val image: String, val vshare_url: String,
                val js: List<String>, val ga_prefix: String, val images: List<String>, val type: Int, val id: Long, val css: List<String>)

/**
 * 新闻额外信息
 */
data class Extra(val long_comments: Int, val popularity: Int, val short_comments: Int, val comments: Int)

/**
 *评论信息
 */
data class Comment(val author: String, val content: String, val avatar: String, val time: Long, val id: Long, val likes: Int, val reply_to: Comment?)

data class CommentList(val comments:List<Comment>)

