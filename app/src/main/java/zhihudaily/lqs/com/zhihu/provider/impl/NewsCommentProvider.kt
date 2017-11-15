package zhihudaily.lqs.com.zhihu.provider.impl

import android.util.Log
import org.jetbrains.anko.db.insert
import zhihudaily.lqs.com.zhihu.connection.DatailyConnection
import zhihudaily.lqs.com.zhihu.model.dto.DbHelp
import zhihudaily.lqs.com.zhihu.model.dto.NewsTable
import zhihudaily.lqs.com.zhihu.model.mapper.DataMapper
import zhihudaily.lqs.com.zhihu.model.vo.NewsVo
import zhihudaily.lqs.com.zhihu.provider.interfac.INewsProvider
import zhihudaily.lqs.com.zhihu.utils.toVararyArray

/**
 * Created by admin on 2017/10/18.
 */
class NewsCommentProvider : INewsProvider {
    val datailyComment: DatailyConnection by lazy { DatailyConnection() }

    val dataMapper: DataMapper by lazy { DataMapper.instance }

    val dbHelp: DbHelp by lazy { DbHelp.instance }

    companion object {
        val instance: NewsCommentProvider by lazy { NewsCommentProvider() }
    }

    override fun getNewsById(id: Long): NewsVo {
        Log.e("NewsCommentProvider","网络请求News")
        datailyComment.id = id
        val news = datailyComment.execute()
        val newsDb = dataMapper.mapperNews2NewsDb(news)
        dbHelp.use {
            val values = newsDb.map.toVararyArray()
            insert(NewsTable.NAME, *values as Array<out Pair<String, Any>>)
        }
        return dataMapper.mapperNews2NewsVo(news)
    }
}