package zhihudaily.lqs.com.zhihu.provider.impl

import zhihudaily.lqs.com.zhihu.adapter.IItem
import zhihudaily.lqs.com.zhihu.comment.LastNewsComment
import zhihudaily.lqs.com.zhihu.model.mapper.DataMapper
import zhihudaily.lqs.com.zhihu.model.vo.StoryVo
import zhihudaily.lqs.com.zhihu.provider.interfac.IDailyProvider

/**
 * Created by admin on 2017/10/19.
 */
class DailyCommentProvider:IDailyProvider {
    companion object {
        val instance by lazy { DailyCommentProvider() }
    }
    val lastNewsComment: LastNewsComment by lazy { LastNewsComment() }

    val dataMapper:DataMapper by lazy { DataMapper.instance }

    override fun getDailyByDay(day: Long): MutableList<IItem> {
        lastNewsComment.date = day
        val daily = lastNewsComment.execute()

        return dataMapper.mapperDaily2Vo(daily)
    }
}