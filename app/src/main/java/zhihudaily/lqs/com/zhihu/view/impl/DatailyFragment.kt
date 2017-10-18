package zhihudaily.lqs.com.zhihu.view.impl

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_dataily.*
import kotlinx.android.synthetic.main.content_scrolling.*
import kotlinx.android.synthetic.main.fragment_dataily.*

import zhihudaily.lqs.com.zhihu.R
import zhihudaily.lqs.com.zhihu.model.vo.NewsVo
import zhihudaily.lqs.com.zhihu.presenter.impl.DatailyPresenter
import zhihudaily.lqs.com.zhihu.view.interfac.IDatailyView
import kotlin.properties.Delegates


class DatailyFragment : AbsFragment<DatailyPresenter>(), IDatailyView {
    companion object {
        val ID_KEY = "id_key"
    }

    var mNewsVo: NewsVo? = null

    override val presenter: DatailyPresenter by lazy { DatailyPresenter().apply { view = this@DatailyFragment } }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            mNewsVo?.apply {
                webView?.loadData(html, "text/HTML", "utf-8")
                (activity as DatailyActivity).replaceToolbar(title, image)
            }
        }
    }

    override fun replaceData(newsVo: NewsVo) {
//        toolbar_layout.title = newsVo.title
//        image.setImageURI(newsVo.image)

        if (userVisibleHint) {
            webView?.loadData(newsVo.html, "text/HTML", "utf-8")
            (activity as DatailyActivity).replaceToolbar(newsVo.title, newsVo.image)
        }
        this.mNewsVo = newsVo
    }

    override val layoutRse = R.layout.fragment_dataily

    override fun initData() {
        val id = arguments.getLong(ID_KEY, 0)
        presenter.replaceData(id)
    }

    override fun initUi(view: View?) {
//        (activity as AppCompatActivity).setSupportActionBar(toolbar)
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }
    }


}
