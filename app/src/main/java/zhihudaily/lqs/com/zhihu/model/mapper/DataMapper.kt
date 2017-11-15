package zhihudaily.lqs.com.zhihu.model.mapper

import zhihudaily.lqs.com.zhihu.adapter.IItem
import zhihudaily.lqs.com.zhihu.model.dto.*
import zhihudaily.lqs.com.zhihu.model.vo.*

/**
 * Created by admin on 2017/8/31.
 */
class DataMapper {
    companion object {
        val instance by lazy { DataMapper() }
    }

    fun mapperDaily2Vo(daily: Daily): MutableList<IItem> {
        val list = ArrayList<IItem>()
        val storyVoList = daily.stories.map {
            StoryVo(it.images[0], it.type, it.id, it.ga_prefix, it.title)
        }
        list.addAll(storyVoList)
        daily.top_stories?.apply {
            val topStoryVolist = this.map { StoryVo(it.image, it.type, it.id, it.ga_prefix, it.title) }
            val topStorysVo = TopStorysVo(topStoryVolist)
            list.add(0, topStorysVo)
        }
        return list
    }

    fun News.creatHtml(): String {
        val buffer = StringBuffer(this.body.replace("img-place-holder", ""))
        buffer.insert(0, "</head>")
        this.css.forEach {
            buffer.insert(0, "<link rel=\"stylesheet\" href=\"${it}\" type=\"text/css\" />")
        }
        buffer.insert(0, "<html><head>")
        buffer.append("</html>")
        return buffer.toString()
    }

    fun mapperNews2NewsVo(news: News): NewsVo {
        val html = news.creatHtml()
        return NewsVo(html, news.title, news.image, news.type, news.id)
    }

    fun mapperStoryDbList2Vo(list: List<StoryDb>): MutableList<IItem> {
        val arrayList = ArrayList<IItem>()
        arrayList.addAll(list.map {
            StoryVo(it.image, 0, it._id, it.ga_prefix, it.title)
        })
        return arrayList
    }

    fun mapperNewsDb2NewsVo(newsDb: NewsDb): NewsVo = newsDb.let {
        NewsVo(it.html, it.title, it.image, it.type, it._id)
    }

    fun mapperDaily2StoryDbList(daily: Daily): List<StoryDb> = daily.stories.map {
        StoryDb(it.id, it.title, it.images[0], daily.date, it.ga_prefix)
    }

    fun mapperNews2NewsDb(news: News): NewsDb = news.let {
        NewsDb(it.id, it.title, it.type, it.image, news.creatHtml())
    }

    fun mapperCommentList2Vo(list: List<Comment>): MutableList<IItem> {
        val result = ArrayList<IItem>()
        list.forEach {
            result.add(CommentItemHeadVo(it.avatar, it.author, it.likes, it))
            result.add(CommentItemContentVo(it.content, it))
            it.reply_to?.apply {
                result.add(CommentTtemReplyToVo(it.reply_to.author, it.reply_to.content, it))
            }
            result.add(CommentItemFootVo(it.time, it))
        }
        return result
    }

    fun mapperStoryVo2StoryDb(vo: StoryVo, date: Long): StoryDb = StoryDb(vo.id, vo.title, vo.images, date, vo.ga_prefix)

    fun mapperThemesToThemeVoList(themes:Themes)= themes.others.map {
        ThemeVo(it.name,it.id)
    }
}