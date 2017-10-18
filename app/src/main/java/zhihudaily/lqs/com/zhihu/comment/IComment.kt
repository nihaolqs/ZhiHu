package zhihudaily.lqs.com.zhihu.comment

/**
 * Created by admin on 2017/8/31.
 */
interface IComment<T> {
    companion object {
        val HOST:String = "https://news-at.zhihu.com/api/4/"
    }
    fun execute(): T
}