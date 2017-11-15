package zhihudaily.lqs.com.zhihu.model.impl

import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import zhihudaily.lqs.com.zhihu.connection.IConnection
import zhihudaily.lqs.com.zhihu.connection.IConnection.Companion.HOST
import zhihudaily.lqs.com.zhihu.model.dto.Themes
import zhihudaily.lqs.com.zhihu.model.interfac.IModel
import zhihudaily.lqs.com.zhihu.model.interfac.IThemesListModel
import zhihudaily.lqs.com.zhihu.model.mapper.DataMapper
import zhihudaily.lqs.com.zhihu.presenter.interfac.IThemesListPresenter
import java.net.URL

/**
 * Created by admin on 2017/11/7.
 */
class ThemesListModel(override var presenter: IThemesListPresenter) : IConnection<Themes>, IModel<IThemesListPresenter>, IThemesListModel {
    override fun getThemeList() {
        doAsync {
            val themes: Themes = execute();
            val themeVoList = DataMapper.instance.mapperThemesToThemeVoList(themes)
            uiThread {
                presenter.notifiThemeViewDatasetChanged(themeVoList)
            }
        }
    }

    companion object {
        val URL_STRING = HOST + "themes";
    }

    override fun execute(): Themes {
        val jsonString = URL(URL_STRING).readText()
        val themes = Gson().fromJson<Themes>(jsonString, Themes::class.java)
        return themes
    }
}