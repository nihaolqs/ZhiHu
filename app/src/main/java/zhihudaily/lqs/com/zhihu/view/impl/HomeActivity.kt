package zhihudaily.lqs.com.zhihu.view.impl

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.content_home.*
import org.jetbrains.anko.toast
import zhihudaily.lqs.com.zhihu.R
import zhihudaily.lqs.com.zhihu.model.vo.ThemeVo
import zhihudaily.lqs.com.zhihu.presenter.impl.ThemesListPresenter
import zhihudaily.lqs.com.zhihu.presenter.interfac.IThemesListPresenter
import zhihudaily.lqs.com.zhihu.utils.minus
import zhihudaily.lqs.com.zhihu.view.interfac.IThemesView
import zhihudaily.lqs.com.zhihu.view.interfac.IView
import java.util.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, IThemesView, IView<IThemesListPresenter> {

    override val presenter: IThemesListPresenter by lazy { ThemesListPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()


        nav_view.setNavigationItemSelectedListener(this)
        viewPage.adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                val bundle = Bundle()
                bundle.putSerializable(DailyFragment.DATE_KET, (Date() - position))
                return AbsFragment.getInstance(bundle, ::DailyFragment).apply { arguments = bundle }
            }

            override fun getCount(): Int = 365

        }

        presenter.getThemeVoList()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        this.toast("click menu : ${item.title}")
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun notifuUiDataChanged(voList: List<ThemeVo>) {
        nav_view.menu.apply {
            voList.forEach {
                val id = it.id.toInt()
                add(R.id.menuGroup, id, 0, it.name)
            }
        }
    }
}
