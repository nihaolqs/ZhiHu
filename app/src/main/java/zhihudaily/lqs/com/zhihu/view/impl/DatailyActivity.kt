package zhihudaily.lqs.com.zhihu.view.impl

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_dataily.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_scrolling.*
import zhihudaily.lqs.com.zhihu.R
import zhihudaily.lqs.com.zhihu.adapter.IItem
import zhihudaily.lqs.com.zhihu.model.vo.NewsVo
import zhihudaily.lqs.com.zhihu.model.vo.StoryVo
import zhihudaily.lqs.com.zhihu.model.vo.TopStorysVo
import zhihudaily.lqs.com.zhihu.presenter.impl.DailyFragmentPresenter
import zhihudaily.lqs.com.zhihu.presenter.impl.DatailyPresenter
import zhihudaily.lqs.com.zhihu.utils.fromFormatLongDay
import zhihudaily.lqs.com.zhihu.utils.minus
import zhihudaily.lqs.com.zhihu.utils.toFormatLongDay
import zhihudaily.lqs.com.zhihu.view.interfac.IDailyFragmentView
import zhihudaily.lqs.com.zhihu.view.interfac.IDatailyActivityView
import zhihudaily.lqs.com.zhihu.view.interfac.IDatailyView
import zhihudaily.lqs.com.zhihu.view.interfac.IView
import java.util.*

class DatailyActivity : AppCompatActivity(), IDailyFragmentView, IView<DailyFragmentPresenter>, IDatailyActivityView {
    companion object {
        val DATE_KEY = "id_key"
        val POSITION_KEY = "position_key"
    }

    val storyVoList = ArrayList<IItem>()

    var position = 0

    override val presenter: DailyFragmentPresenter by lazy { DailyFragmentPresenter().apply { view = this@DatailyActivity } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dataily)
        setSupportActionBar(toolbar)
        position = intent.getIntExtra(POSITION_KEY, 0)
        datailyViewPage.adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                moreStory(position)
                val bundle = Bundle()
                bundle.putLong(DatailyFragment.ID_KEY, (storyVoList[position] as StoryVo).id)
                return AbsFragment.getInstance(bundle, ::DatailyFragment)
            }

            override fun getCount(): Int = storyVoList.size
        }
        presenter.replaceData(intent.getSerializableExtra(DATE_KEY) as Date)
    }

    override fun notifyDataChange(list: MutableList<IItem>) {
        addStoryVo(list)
    }

    override fun addStoryVo(list: MutableList<IItem>) {
        if (list[0].itemType == TopStorysVo.TYPE) {
            list.removeAt(0)
            addStoryVo(list)
        } else {
            val listPrefix = (list[0] as StoryVo).ga_prefix.toLong()
            val storyVoPrefix1 = if (storyVoList.size > 0) (storyVoList[0] as StoryVo).ga_prefix.toLong() else 0
            if (listPrefix > storyVoPrefix1) {
                storyVoList.addAll(0, list)
                datailyViewPage.adapter.notifyDataSetChanged()
                datailyViewPage.currentItem += list.size
            } else {
                storyVoList.addAll(list)
                datailyViewPage.adapter.notifyDataSetChanged()
            }
            if (list.size == storyVoList.size) datailyViewPage.setCurrentItem(position, false)
        }
    }

    fun replaceToolbar(title: String, imageUrl: String) {
        toolbar_layout.title = title
        image.setImageURI(imageUrl)
    }

    val currentDate = Date().toFormatLongDay()

    fun moreStory(position: Int) {
//        if (position == 0 && (storyVoList[position] as StoryVo).ga_prefix.toLong() < currentDate) {
//            val longDate = (storyVoList[position] as StoryVo).ga_prefix.toLong() / 100 + 20170000
//            val day = this.fromFormatLongDay(longDate - (-2)).toFormatLongDay()
//            presenter.replaceData(day)
//
//        } else if (position == storyVoList.size - 1) {
//            val longDate = (storyVoList[position] as StoryVo).ga_prefix.toLong() / 100 + 20170000
//            val day = (this.fromFormatLongDay(longDate)).toFormatLongDay()
//            presenter.replaceData(day)
//        }
    }
}
