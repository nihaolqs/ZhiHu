package zhihudaily.lqs.com.zhihu.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import zhihudaily.lqs.com.zhihu.R
import zhihudaily.lqs.com.zhihu.utils.minus
import zhihudaily.lqs.com.zhihu.utils.toFormatLongDay
import zhihudaily.lqs.com.zhihu.view.impl.AbsFragment
import zhihudaily.lqs.com.zhihu.view.impl.DailyFragment
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
