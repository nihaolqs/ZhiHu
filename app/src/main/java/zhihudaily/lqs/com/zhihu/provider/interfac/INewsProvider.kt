package zhihudaily.lqs.com.zhihu.provider.interfac

import zhihudaily.lqs.com.zhihu.model.vo.NewsVo

/**
 * Created by admin on 2017/10/18.
 */
interface INewsProvider {
    fun getNewsById(id:Long):NewsVo?
}