package zhihudaily.lqs.com.zhihu.view.interfac

import zhihudaily.lqs.com.zhihu.adapter.IItem
import zhihudaily.lqs.com.zhihu.model.vo.StoryVo

/**
 * Created by admin on 2017/9/19.
 */
interface IDatailyActivityView {
    fun addStoryVo(list: MutableList<IItem>)
}