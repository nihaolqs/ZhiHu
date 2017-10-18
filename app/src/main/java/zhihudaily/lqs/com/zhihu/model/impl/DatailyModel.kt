package zhihudaily.lqs.com.zhihu.model.impl

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import zhihudaily.lqs.com.zhihu.comment.DatailyComment
import zhihudaily.lqs.com.zhihu.model.interfac.IDatailyModel
import zhihudaily.lqs.com.zhihu.model.interfac.IModel
import zhihudaily.lqs.com.zhihu.model.mapper.DataMapper
import zhihudaily.lqs.com.zhihu.presenter.impl.DatailyPresenter
import kotlin.properties.Delegates

/**
 * Created by admin on 2017/9/14.
 */
class DatailyModel : IDatailyModel, IModel<DatailyPresenter> {

    override var presenter: DatailyPresenter by Delegates.notNull<DatailyPresenter>()

    val comment: DatailyComment by lazy { DatailyComment() }

    override fun getData(id: Long) {
        doAsync {
            comment.id = id
            val news = comment.execute()
            val newsVo = DataMapper.instance.mapperNews2NewsVo(news)
            uiThread {
                presenter.notifiReplaceData(newsVo)
            }
        }
    }
}