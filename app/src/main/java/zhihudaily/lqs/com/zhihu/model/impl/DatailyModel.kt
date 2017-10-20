package zhihudaily.lqs.com.zhihu.model.impl

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import zhihudaily.lqs.com.zhihu.comment.DatailyComment
import zhihudaily.lqs.com.zhihu.model.interfac.IDatailyModel
import zhihudaily.lqs.com.zhihu.model.interfac.IModel
import zhihudaily.lqs.com.zhihu.model.mapper.DataMapper
import zhihudaily.lqs.com.zhihu.presenter.impl.DatailyPresenter
import zhihudaily.lqs.com.zhihu.provider.impl.NewsProvider
import kotlin.properties.Delegates

/**
 * Created by admin on 2017/9/14.
 */
class DatailyModel : IDatailyModel, IModel<DatailyPresenter> {

    override var presenter: DatailyPresenter by Delegates.notNull<DatailyPresenter>()

    val provider: NewsProvider by lazy { NewsProvider() }

    override fun getData(id: Long) {
        doAsync {
            val newsVo = provider.getNewsById(id)
            uiThread {
                presenter.notifiReplaceData(newsVo!!)
            }
        }
    }
}