package zhihudaily.lqs.com.zhihu.model.impl

import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import zhihudaily.lqs.com.zhihu.adapter.IItem
import zhihudaily.lqs.com.zhihu.connection.IConnection.Companion.HOST
import zhihudaily.lqs.com.zhihu.model.dto.Comment
import zhihudaily.lqs.com.zhihu.model.dto.CommentList
import zhihudaily.lqs.com.zhihu.model.interfac.ICommentModel
import zhihudaily.lqs.com.zhihu.model.interfac.IModel
import zhihudaily.lqs.com.zhihu.model.mapper.DataMapper
import zhihudaily.lqs.com.zhihu.model.vo.CommentTitle
import zhihudaily.lqs.com.zhihu.presenter.interfac.ICommentPresenter
import java.net.URL

/**
 * Created by admin on 2017/10/25.
 */
class CommentModel(override var presenter: ICommentPresenter) : IModel<ICommentPresenter>, ICommentModel {
    override var isShowLongComment: Boolean = true
    override var isShowShortComment: Boolean = true
    val dataMapper by lazy { DataMapper.instance }
    var longComment: CommentList? = null
    var shortComment: CommentList? = null

    override fun replaceData(id: Long) {
        val gson = Gson()
        doAsync {
            val longUrl = URL(HOST + "story/${id}/long-comments")
            val longCommentJsonStr = longUrl.readText()
            longComment = gson.fromJson(longCommentJsonStr,CommentList::class.java)
            uiThread {
                getData()
            }
        }
        doAsync {
            val shortUrl = URL(HOST + "story/${id}/short-comments")
            val shortCommentJsonStr = shortUrl.readText()
            shortComment = gson.fromJson(shortCommentJsonStr,CommentList::class.java)
            uiThread {
                getData()
            }
        }
    }

    override fun getData() {
        val list = ArrayList<IItem>();
        longComment?.apply {
            list.add(CommentTitle("${comments.size}条长评"))
            if (isShowLongComment) {
                val longCommentVoList = dataMapper.mapperCommentList2Vo(comments)
                list.addAll(longCommentVoList)
            }
            shortComment?.apply {
                list.add(CommentTitle("${comments.size}条短评"))
                if (isShowShortComment) {
                    val shortCommentVoList = dataMapper.mapperCommentList2Vo(comments)
                    list.addAll(shortCommentVoList)
                }
                presenter.notifiViewReplaceData(list)
            }
        }
    }
}