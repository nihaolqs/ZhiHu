package zhihudaily.lqs.com.zhihu.presenter.interfac

import zhihudaily.lqs.com.zhihu.model.vo.NewsVo

/**
 * Created by admin on 2017/9/14.
 */
interface IDatailyPresenter {
    fun replaceData(id :Long)
    fun notifiReplaceData(newsVo: NewsVo)
}