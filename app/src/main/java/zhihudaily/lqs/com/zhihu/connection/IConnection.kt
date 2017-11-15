package zhihudaily.lqs.com.zhihu.connection

/**
 * Created by admin on 2017/8/31.
 */
interface IConnection<T> {
    companion object {
        val HOST:String = "https://news-at.zhihu.com/api/4/"
    }
    fun execute(): T
}