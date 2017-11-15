package zhihudaily.lqs.com.zhihu.presenter.impl

import zhihudaily.lqs.com.zhihu.adapter.IItem
import zhihudaily.lqs.com.zhihu.model.impl.CommentModel
import zhihudaily.lqs.com.zhihu.model.interfac.ICommentModel
import zhihudaily.lqs.com.zhihu.presenter.interfac.ICommentPresenter
import zhihudaily.lqs.com.zhihu.presenter.interfac.IPresenter
import zhihudaily.lqs.com.zhihu.view.interfac.ICommentView

/**
 * Created by admin on 2017/10/25.
 */
class CommentPresenter(override var view: ICommentView) : IPresenter<ICommentModel, ICommentView>, ICommentPresenter {
    override val model: ICommentModel by lazy { CommentModel(this) }

    override fun getComment(id: Long) {
        model.replaceData(id)
    }

    override fun closeOrShowLongComment() {
        model.isShowLongComment = !model.isShowLongComment
    }

    override fun closeOrShowShortComment() {
        model.isShowShortComment = !model.isShowShortComment
    }

    override fun notifiViewReplaceData(list: List<IItem>) {
        view.showCommentList(list)
    }
}