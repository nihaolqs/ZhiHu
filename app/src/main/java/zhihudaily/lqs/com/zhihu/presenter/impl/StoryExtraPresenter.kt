package zhihudaily.lqs.com.zhihu.presenter.impl

import zhihudaily.lqs.com.zhihu.model.dto.Extra
import zhihudaily.lqs.com.zhihu.model.impl.StoryExtraModel
import zhihudaily.lqs.com.zhihu.model.interfac.IStoryExtraModel
import zhihudaily.lqs.com.zhihu.presenter.interfac.IPresenter
import zhihudaily.lqs.com.zhihu.presenter.interfac.IStoryExtraPresenter
import zhihudaily.lqs.com.zhihu.view.impl.DatailyActivity
import zhihudaily.lqs.com.zhihu.view.interfac.IStoryExtraView
import kotlin.properties.Delegates

/**
 * Created by admin on 2017/10/23.
 */
class StoryExtraPresenter(override var view: IStoryExtraView) : IStoryExtraPresenter, IPresenter<IStoryExtraModel, IStoryExtraView> {
    override fun getStoryExtra(id: Long) {
        model.getData(id)
    }

    override fun replaceViewData(extra: Extra) {
        view.replaceView(extra)
    }

    override val model: IStoryExtraModel by lazy { StoryExtraModel(this) }

}