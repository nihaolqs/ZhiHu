package zhihudaily.lqs.com.zhihu.provider.interfac

import zhihudaily.lqs.com.zhihu.adapter.IItem
import zhihudaily.lqs.com.zhihu.model.vo.StoryVo
import java.util.*

/**
 * Created by admin on 2017/10/19.
 */

interface IDailyProvider {
    fun getDailyByDay(date: Date): MutableList<IItem>?
}