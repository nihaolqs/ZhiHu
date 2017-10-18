package zhihudaily.lqs.com.zhihu.view.impl

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import zhihudaily.lqs.com.zhihu.application.MyApp
import zhihudaily.lqs.com.zhihu.utils.getLayoutInflater
import zhihudaily.lqs.com.zhihu.view.interfac.IView

/**
 * Created by admin on 2017/8/30.
 */
abstract class AbsFragment<T> : Fragment(), IView<T> {
    companion object {
        inline fun <T,U : AbsFragment<T>> getInstance(bundle: Bundle, factory: () -> U): U {
            val fragment = factory()
            fragment.arguments = bundle
            return fragment
        }
    }

    /**
     * layout Id
     */
    abstract val layoutRse: Int

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val layout = MyApp.instance.getLayoutInflater().inflate(layoutRse, null)
//        initUi(layout)
        return layout
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi(view)
        initData()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    abstract fun initData()

    abstract fun initUi(view: View?)

}