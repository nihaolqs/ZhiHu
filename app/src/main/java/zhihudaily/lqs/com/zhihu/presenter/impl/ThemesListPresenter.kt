package zhihudaily.lqs.com.zhihu.presenter.impl

import zhihudaily.lqs.com.zhihu.model.impl.ThemesListModel
import zhihudaily.lqs.com.zhihu.model.interfac.IThemesListModel
import zhihudaily.lqs.com.zhihu.model.vo.ThemeVo
import zhihudaily.lqs.com.zhihu.presenter.interfac.IPresenter
import zhihudaily.lqs.com.zhihu.presenter.interfac.IThemesListPresenter
import zhihudaily.lqs.com.zhihu.view.interfac.IThemesView

/**
 * Created by admin on 2017/11/7.
 */
class ThemesListPresenter(override var view: IThemesView) :IThemesListPresenter,IPresenter<IThemesListModel,IThemesView>{
    override val model by lazy { ThemesListModel(this) }

    override fun getThemeVoList() {
        this.model.getThemeList()
    }

    override fun notifiThemeViewDatasetChanged(voList: List<ThemeVo>) {
        this.view.notifuUiDataChanged(voList)
    }
}