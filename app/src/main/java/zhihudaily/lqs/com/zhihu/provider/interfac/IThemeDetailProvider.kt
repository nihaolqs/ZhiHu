package zhihudaily.lqs.com.zhihu.provider.interfac

import zhihudaily.lqs.com.zhihu.model.dto.ThemeDetail

/**
 * Created by admin on 2017/11/14.
 */
interface IThemeDetailProvider {
    fun getThemeDatilById(id: Long) : ThemeDetail
}