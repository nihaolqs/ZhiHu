package zhihudaily.lqs.com.zhihu.connection

import android.util.Log
import com.google.gson.Gson
import zhihudaily.lqs.com.zhihu.connection.IConnection.Companion.HOST
import zhihudaily.lqs.com.zhihu.model.dto.Daily
import zhihudaily.lqs.com.zhihu.utils.toFormatLongDay
import java.net.URL
import java.util.*

/**
 * Created by admin on 2017/8/31.
 */
class LastNewsConnection : IConnection<Daily>{
    companion object {
        val LAST_NEWS_URL = HOST + "news/latest"
        val HISTORY_NEWS_URL = HOST + "news/before/"
    }

    var date:Long? = null

    override fun execute(): Daily {
        Log.e("jsonString","start")
        var cpDate = date
        val url = if(cpDate != null && cpDate < Date().toFormatLongDay()+1) HISTORY_NEWS_URL + cpDate else LAST_NEWS_URL
        val jsonString = URL(url).readText()
        Log.e("jsonString",date.toString())
        val daily = Gson().fromJson<Daily>(jsonString, Daily::class.java)
        return daily
    }
}