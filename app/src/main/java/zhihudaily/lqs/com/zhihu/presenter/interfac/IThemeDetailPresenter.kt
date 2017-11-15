package zhihudaily.lqs.com.zhihu.presenter.interfac

import zhihudaily.lqs.com.zhihu.adapter.IItem

/**
 * Created by linqs on 2017/11/15.
 */
interface IThemeDetailPresenter {
    fun getThemeDetailDataById(id:Long)
    fun notifiHeadUiReplace()
    fun notifiEditorListReplace(list:List<IItem>)
    fun notifiStoryListReplace(list:List<IItem>);
}