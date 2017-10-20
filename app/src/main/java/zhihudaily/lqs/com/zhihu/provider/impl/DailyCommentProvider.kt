package zhihudaily.lqs.com.zhihu.provider.impl

import android.util.Log
import org.jetbrains.anko.db.insert
import zhihudaily.lqs.com.zhihu.adapter.IItem
import zhihudaily.lqs.com.zhihu.comment.LastNewsComment
import zhihudaily.lqs.com.zhihu.model.dto.DbHelp
import zhihudaily.lqs.com.zhihu.model.dto.StoryTable
import zhihudaily.lqs.com.zhihu.model.mapper.DataMapper
import zhihudaily.lqs.com.zhihu.model.vo.StoryVo
import zhihudaily.lqs.com.zhihu.provider.interfac.IDailyProvider
import zhihudaily.lqs.com.zhihu.utils.minus
import zhihudaily.lqs.com.zhihu.utils.toFormatLongDay
import zhihudaily.lqs.com.zhihu.utils.toVararyArray
import java.util.*

/**
 * Created by admin on 2017/10/19.
 */
class DailyCommentProvider : IDailyProvider {
    companion object {
        val instance by lazy { DailyCommentProvider() }
    }

    val lastNewsComment: LastNewsComment by lazy { LastNewsComment() }

    val dataMapper: DataMapper by lazy { DataMapper.instance }

    val dbHelp by lazy { DbHelp.instance }

    override fun getDailyByDay(date: Date): MutableList<IItem> {
        Log.e("DailyCommentProvider","Daily网络请求")
        lastNewsComment.date = (date -(-1)).toFormatLongDay()
        val daily = lastNewsComment.execute()
        if (daily.date < Date().toFormatLongDay()) {
            dataMapper.mapperDaily2StoryDbList(daily).forEach {
                dbHelp.use {
                    val array = it.map.toVararyArray()
                    insert(StoryTable.NAME, *array )
                }
            }
        }
        return dataMapper.mapperDaily2Vo(daily)
    }
}