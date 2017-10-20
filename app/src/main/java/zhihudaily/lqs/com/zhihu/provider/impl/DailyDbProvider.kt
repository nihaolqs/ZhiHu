package zhihudaily.lqs.com.zhihu.provider.impl

import android.util.Log
import org.jetbrains.anko.db.select
import zhihudaily.lqs.com.zhihu.adapter.IItem
import zhihudaily.lqs.com.zhihu.model.dto.DbHelp
import zhihudaily.lqs.com.zhihu.model.dto.StoryDb
import zhihudaily.lqs.com.zhihu.model.dto.StoryTable
import zhihudaily.lqs.com.zhihu.model.mapper.DataMapper
import zhihudaily.lqs.com.zhihu.provider.interfac.IDailyProvider
import zhihudaily.lqs.com.zhihu.utils.parseList
import zhihudaily.lqs.com.zhihu.utils.toFormatLongDay
import java.util.*

/**
 * Created by admin on 2017/10/20.
 */
class DailyDbProvider : IDailyProvider {

    companion object {
        val instance by lazy { DailyDbProvider() }
    }

    val dataMapper by lazy { DataMapper.instance }

    val dbHelp by lazy { DbHelp.instance }

    override fun getDailyByDay(date: Date): MutableList<IItem>? {
        Log.e("DailyDbProvider","getDailyByDay:" + date.toFormatLongDay())

        val mutableList = dbHelp.use {
            select(StoryTable.NAME)
                    .whereSimple("${StoryTable.DATE} = ?", date.toFormatLongDay().toString())
                    .parseList { StoryDb(HashMap(it)) }
        }.let {
            if (it.size > 0) dataMapper.mapperStoryDbList2Vo(it) else null
        }
        Log.e("DailyDbProvider" ,mutableList?.size.toString())
        return mutableList
    }
}