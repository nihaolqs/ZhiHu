package zhihudaily.lqs.com.zhihu.view.interfac

import zhihudaily.lqs.com.zhihu.adapter.IItem

/**
 * Created by admin on 2017/9/6.
 */
interface IDailyFragmentView {
    abstract fun notifyDataChange(list: MutableList<IItem>)
}