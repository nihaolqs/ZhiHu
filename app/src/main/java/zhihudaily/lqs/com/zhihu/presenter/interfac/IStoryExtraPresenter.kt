package zhihudaily.lqs.com.zhihu.presenter.interfac

import zhihudaily.lqs.com.zhihu.model.dto.Extra

/**
 * Created by admin on 2017/10/23.
 */
interface IStoryExtraPresenter {
    fun getStoryExtra(id: Long)
    fun replaceViewData(extra: Extra)
}