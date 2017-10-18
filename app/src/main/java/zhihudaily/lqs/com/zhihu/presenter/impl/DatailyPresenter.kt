package zhihudaily.lqs.com.zhihu.presenter.impl

import zhihudaily.lqs.com.zhihu.model.impl.DatailyModel
import zhihudaily.lqs.com.zhihu.model.vo.NewsVo
import zhihudaily.lqs.com.zhihu.presenter.interfac.IDatailyPresenter
import zhihudaily.lqs.com.zhihu.presenter.interfac.IPresenter
import zhihudaily.lqs.com.zhihu.view.impl.DatailyActivity
import zhihudaily.lqs.com.zhihu.view.impl.DatailyFragment
import kotlin.properties.Delegates

/**
 * Created by admin on 2017/9/14.
 */
class DatailyPresenter : IDatailyPresenter, IPresenter<DatailyModel, DatailyFragment> {
    override val model: DatailyModel by lazy { DatailyModel().apply { presenter = this@DatailyPresenter } }

    override var view: DatailyFragment by Delegates.notNull<DatailyFragment>()

    override fun replaceData(id: Long) {
        model.getData(id)
    }

    override fun notifiReplaceData(newsVo: NewsVo) {
        view.replaceData(newsVo)
    }
}