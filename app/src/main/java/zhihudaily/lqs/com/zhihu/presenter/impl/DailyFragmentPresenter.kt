package zhihudaily.lqs.com.zhihu.presenter.impl

import zhihudaily.lqs.com.zhihu.adapter.IItem
import zhihudaily.lqs.com.zhihu.model.impl.DailyFragmentModel
import zhihudaily.lqs.com.zhihu.presenter.interfac.IDailyFragmentPresenter
import zhihudaily.lqs.com.zhihu.presenter.interfac.IPresenter
import zhihudaily.lqs.com.zhihu.view.impl.DailyFragment
import zhihudaily.lqs.com.zhihu.view.interfac.IDailyFragmentView
import kotlin.properties.Delegates

/**
 * Created by admin on 2017/8/31.
 */
class DailyFragmentPresenter: IPresenter<DailyFragmentModel,IDailyFragmentView>, IDailyFragmentPresenter {

    override val model: DailyFragmentModel by lazy<DailyFragmentModel>{ DailyFragmentModel().apply { presenter = this@DailyFragmentPresenter } }

    override var view: IDailyFragmentView by Delegates.notNull<IDailyFragmentView>()
    fun notifiDataForChange(vo: MutableList<IItem>) {
        view.notifyDataChange(vo)
    }

    override fun replaceData(date:Long){
        model.getData(date)
    }
}