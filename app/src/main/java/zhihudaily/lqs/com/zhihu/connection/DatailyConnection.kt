package zhihudaily.lqs.com.zhihu.connection

import com.google.gson.Gson
import zhihudaily.lqs.com.zhihu.connection.IConnection.Companion.HOST
import zhihudaily.lqs.com.zhihu.model.dto.News
import java.net.URL
import kotlin.properties.Delegates

/**
 * Created by admin on 2017/9/13.
 */
class DatailyConnection : IConnection<News> {
    companion object {
        val NEWS_URI = HOST+"news/"
    }

    var id: Long by Delegates.notNull<Long>()
    override fun execute(): News {
        val uri = NEWS_URI + id
        val jsonString = URL(uri).readText()
        val news = Gson().fromJson<News>(jsonString, News::class.java)
        return news
    }
}