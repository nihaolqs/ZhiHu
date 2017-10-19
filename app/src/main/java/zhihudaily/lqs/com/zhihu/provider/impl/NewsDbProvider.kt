package zhihudaily.lqs.com.zhihu.provider.impl

import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.SelectQueryBuilder
import org.jetbrains.anko.db.select
import zhihudaily.lqs.com.zhihu.model.dto.DbHelp
import zhihudaily.lqs.com.zhihu.model.dto.NewsDb
import zhihudaily.lqs.com.zhihu.model.dto.NewsTable
import zhihudaily.lqs.com.zhihu.model.mapper.DataMapper
import zhihudaily.lqs.com.zhihu.model.vo.NewsVo
import zhihudaily.lqs.com.zhihu.provider.interfac.INewsProvider
import zhihudaily.lqs.com.zhihu.utils.parseOpt

/**
 * Created by admin on 2017/10/18.
 */
class NewsDbProvider : INewsProvider {
    val dataMapper: DataMapper by lazy { DataMapper.instance }
    val dbHelp: DbHelp by lazy { DbHelp.instance }

    companion object {
        val instance by lazy { NewsDbProvider() }
    }

    override fun getNewsById(id: Long): NewsVo? =
            dbHelp.use {
                val newsRequest = "${NewsTable.ID} = ?"
                select(NewsTable.NAME)
                        .whereSimple(newsRequest, id.toString())
                        .parseOpt { columns: Map<String, Any> -> NewsDb(HashMap(columns)) }
            }.let {
                it?.let { it1 -> dataMapper.mapperNewsDb2NewsVo(it1) } ?: null
            }
}



