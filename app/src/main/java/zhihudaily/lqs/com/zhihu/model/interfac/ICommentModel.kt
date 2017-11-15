package zhihudaily.lqs.com.zhihu.model.interfac

/**
 * Created by admin on 2017/10/25.
 */
interface ICommentModel {
    var isShowLongComment: Boolean
    var isShowShortComment: Boolean
    fun replaceData(id: Long)
    fun getData()
}