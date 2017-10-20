package zhihudaily.lqs.com.zhihu.model.impl

import android.support.annotation.UiThread
import android.util.Log
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import zhihudaily.lqs.com.zhihu.comment.LastNewsComment
import zhihudaily.lqs.com.zhihu.model.interfac.IDailyFragmentModel
import zhihudaily.lqs.com.zhihu.model.interfac.IModel
import zhihudaily.lqs.com.zhihu.model.mapper.DataMapper
import zhihudaily.lqs.com.zhihu.presenter.impl.DailyFragmentPresenter
import zhihudaily.lqs.com.zhihu.presenter.interfac.IPresenter
import zhihudaily.lqs.com.zhihu.provider.impl.DailyProvider
import zhihudaily.lqs.com.zhihu.utils.toFormatLongDay
import java.util.*
import kotlin.properties.Delegates

/**
 * Created by admin on 2017/8/31.
 */
class DailyFragmentModel : IModel<DailyFragmentPresenter>, IDailyFragmentModel {
    override var presenter: DailyFragmentPresenter by Delegates.notNull<DailyFragmentPresenter>()

    val dailyProvider by lazy { DailyProvider() }

    override fun getData(date: Date) {
        doAsync {
            val vo = dailyProvider.getDailyByDay(date)
           uiThread {
               if (vo != null) {
                   presenter.notifiDataForChange(vo)
               }
           }
        }
    }
}