package zhihudaily.lqs.com.zhihu.provider.impl

import zhihudaily.lqs.com.zhihu.adapter.IItem
import zhihudaily.lqs.com.zhihu.model.vo.StoryVo
import zhihudaily.lqs.com.zhihu.provider.interfac.IDailyProvider
import zhihudaily.lqs.com.zhihu.utils.firstResult

/**
 * Created by admin on 2017/10/19.
 */
class DailyProvider(val providers: List<IDailyProvider> = listOf(DailyCommentProvider.instance)) : IDailyProvider {

    companion object {
        val instance by lazy { DailyProvider() }
    }

    override fun getDailyByDay(day: Long): MutableList<IItem> =
            providers.firstResult { it.getDailyByDay(day) }
}