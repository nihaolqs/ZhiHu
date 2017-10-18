package zhihudaily.lqs.com.zhihu.provider

import zhihudaily.lqs.com.zhihu.comment.IComment
import zhihudaily.lqs.com.zhihu.comment.LastNewsComment
import zhihudaily.lqs.com.zhihu.model.mapper.DataMapper
import zhihudaily.lqs.com.zhihu.model.vo.StoryVo

/**
* Created by admin on 2017/8/31.
*/

interface IProvider<T>{
    fun providerData():T
}

//class LastNewsProvider :IProvider<List<StoryVo>>{
//    override fun providerData(): List<StoryVo> {
//        val daily = LastNewsComment().execute()
//        return DataMapper.instance.mapperToStoryVos(daily.stories)
//    }
//
//}