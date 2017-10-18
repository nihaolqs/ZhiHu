package zhihudaily.lqs.com.zhihu.presenter.interfac

/**
 * Created by admin on 2017/8/30.
 */
interface IPresenter<M,V> {
    val model : M
    var view :V

}