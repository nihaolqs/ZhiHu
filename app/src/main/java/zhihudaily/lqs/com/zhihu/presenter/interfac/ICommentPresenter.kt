package zhihudaily.lqs.com.zhihu.presenter.interfac

import zhihudaily.lqs.com.zhihu.adapter.IItem

/**
 * Created by admin on 2017/10/25.
 */
interface ICommentPresenter {

    fun getComment(id: Long)

    fun closeOrShowLongComment()

    fun closeOrShowShortComment()

    fun notifiViewReplaceData(list: List<IItem>)
}