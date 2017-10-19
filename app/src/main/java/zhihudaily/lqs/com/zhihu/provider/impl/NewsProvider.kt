package zhihudaily.lqs.com.zhihu.provider.impl

import zhihudaily.lqs.com.zhihu.model.vo.NewsVo
import zhihudaily.lqs.com.zhihu.provider.interfac.INewsProvider
import zhihudaily.lqs.com.zhihu.utils.firstResult

/**
 * Created by admin on 2017/10/19.
 */
class NewsProvider(val proveders: List<INewsProvider> = listOf(NewsDbProvider.instance, NewsCommentProvider.instance)) : INewsProvider {
    companion object {
        val instance by lazy { NewsProvider() }
    }

    override fun getNewsById(id: Long): NewsVo? =
            proveders.firstResult { it.getNewsById(id) }

}