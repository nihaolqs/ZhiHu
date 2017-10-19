package zhihudaily.lqs.com.zhihu.provider.interfac

import zhihudaily.lqs.com.zhihu.adapter.IItem
import zhihudaily.lqs.com.zhihu.model.vo.StoryVo

/**
 * Created by admin on 2017/10/19.
 */

interface IDailyProvider {
    fun getDailyByDay(day: Long): MutableList<IItem>
}