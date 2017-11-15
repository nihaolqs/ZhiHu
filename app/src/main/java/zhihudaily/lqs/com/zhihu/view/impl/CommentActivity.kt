package zhihudaily.lqs.com.zhihu.view.impl

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import kotlinx.android.synthetic.main.activity_comment.*
import kotlinx.android.synthetic.main.comment_item_content.view.*
import kotlinx.android.synthetic.main.comment_item_foot.view.*
import kotlinx.android.synthetic.main.comment_item_head.view.*
import kotlinx.android.synthetic.main.comment_title.view.*
import zhihudaily.lqs.com.zhihu.R
import zhihudaily.lqs.com.zhihu.adapter.IItem
import zhihudaily.lqs.com.zhihu.adapter.RecyclerAdapter
import zhihudaily.lqs.com.zhihu.model.vo.*
import zhihudaily.lqs.com.zhihu.presenter.impl.CommentPresenter
import zhihudaily.lqs.com.zhihu.presenter.interfac.ICommentPresenter
import zhihudaily.lqs.com.zhihu.utils.toFormatDay
import zhihudaily.lqs.com.zhihu.view.interfac.ICommentView
import zhihudaily.lqs.com.zhihu.view.interfac.IView
import java.util.*

/**
 * Created by admin on 2017/10/24.
 */
class CommentActivity : AppCompatActivity(), ICommentView, IView<ICommentPresenter> {
    override val presenter: ICommentPresenter  by lazy { CommentPresenter(this) }

    val dataList = ArrayList<IItem>()

    companion object {
        val DATA_PAIR_KEY = "data_pair_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)
        initUi()
        initData()
    }


    private fun initData() {
        val pair = intent.getSerializableExtra(DATA_PAIR_KEY) as Pair<Long, Int>
        title = "共有${pair.second}条评论"
        presenter.getComment(pair.first)
    }

    fun initUi() {
        rv_comment_list.layoutManager = LinearLayoutManager(this)
        val adapter = RecyclerAdapter(dataList).apply {
            registerView(
                    CommentItemHeadVo.TYPE,
                    R.layout.comment_item_head,
                    { data, view ->
                        val commentItemHeadVo = data as CommentItemHeadVo
                        view.tv_author.text = commentItemHeadVo.author
                        view.sdv_avatar.setImageURI(commentItemHeadVo.avatar)
                        view.tv_likes.text = commentItemHeadVo.likes.toString()
                    },
                    { data, view, posiction ->

                    })
            registerView(
                    CommentItemContentVo.TYPE,
                    R.layout.comment_item_content,
                    { data, view ->
                        val commentItemContentVo = data as CommentItemContentVo
                        view.tv_content.text = commentItemContentVo.content
                    },
                    { data, view, posiction ->

                    })
            registerView(
                    CommentTtemReplyToVo.TYPE,
                    R.layout.comment_item_content,
                    { data, view ->
                        val commentTtemReplyToVo = data as CommentTtemReplyToVo
                        val author = "//" + commentTtemReplyToVo.author + ":-"
                        val content = author + commentTtemReplyToVo.content
                        val spannableContent = SpannableString(content).apply {
                            setSpan(ForegroundColorSpan(Color.parseColor("#FF000000")), 0, author.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                            setSpan(StyleSpan(Typeface.BOLD), 0, author.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                            setSpan(ForegroundColorSpan(Color.parseColor("#FFA0A0A0")), author.length, content.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                        }

                        view.tv_content.text = spannableContent
                    },
                    { data, view, posiction ->

                    })
            registerView(
                    CommentItemFootVo.TYPE,
                    R.layout.comment_item_foot,
                    { data, view ->
                        val commentItemFootVo = data as CommentItemFootVo
                        view.tv_time.text = Date(commentItemFootVo.time * 1000).toFormatDay()
                    },
                    { data, view, posiction ->

                    })
            registerView(
                    CommentTitle.TYPE,
                    R.layout.comment_title,
                    { data, view ->
                        val commentTitle = data as CommentTitle
                        view.tv_title.text = commentTitle.title
                    },
                    { data, view, posiction ->

                    })
        }
        rv_comment_list.adapter = adapter
    }


    override fun showCommentList(list: List<IItem>) {
        dataList.clear()
        dataList.addAll(list)
        rv_comment_list.adapter.notifyDataSetChanged()
    }
}