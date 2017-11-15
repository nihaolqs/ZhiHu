package zhihudaily.lqs.com.zhihu.model.impl

import zhihudaily.lqs.com.zhihu.model.interfac.IModel
import zhihudaily.lqs.com.zhihu.model.interfac.IThemeDetailModel
import zhihudaily.lqs.com.zhihu.provider.impl.ThemeDetailProvider
import zhihudaily.lqs.com.zhihu.provider.interfac.IThemeDetailProvider

/**
 * Created by linqs on 2017/11/15.
 */
class ThemeDetailModel(override var presenter: IThemeDetailProvider) :IModel<IThemeDetailProvider>,IThemeDetailModel {
    override fun getDataById(id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val IThemeDetailProvider by lazy { ThemeDetailProvider() }
}