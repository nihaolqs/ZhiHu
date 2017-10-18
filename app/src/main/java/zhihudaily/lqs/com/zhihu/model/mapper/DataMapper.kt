package zhihudaily.lqs.com.zhihu.model.mapper

import zhihudaily.lqs.com.zhihu.adapter.IItem
import zhihudaily.lqs.com.zhihu.model.dto.Daily
import zhihudaily.lqs.com.zhihu.model.dto.News
import zhihudaily.lqs.com.zhihu.model.vo.NewsVo
import zhihudaily.lqs.com.zhihu.model.vo.StoryVo
import zhihudaily.lqs.com.zhihu.model.vo.TopStorysVo

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

    fun mapperNews2NewsVo(news: News): NewsVo {
        val buffer = StringBuffer(news.body.replace("img-place-holder",""))
        buffer.insert(0,"</head>")
        news.css.forEach {
            buffer.insert(0, "<link rel=\"stylesheet\" href=\"${it}\" type=\"text/css\" />")
        }
        buffer.insert(0,"<html><head>")
        buffer.append("</html>")
        val html = buffer.toString()
        return NewsVo(html,news.title,news.image,news.type,news.id)
    }
}