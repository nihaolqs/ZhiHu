package zhihudaily.lqs.com.zhihu.model.interfac

import zhihudaily.lqs.com.zhihu.utils.toFormatLongDay
import java.util.*

/**
 * Created by admin on 2017/9/6.
 */
interface IDailyFragmentModel {
    fun getData(date: Date)
}