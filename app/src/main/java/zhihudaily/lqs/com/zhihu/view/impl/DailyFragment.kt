package zhihudaily.lqs.com.zhihu.view.impl

import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.banner_item.view.*
import kotlinx.android.synthetic.main.fragment_daily.*
import zhihudaily.lqs.com.zhihu.R
import zhihudaily.lqs.com.zhihu.adapter.IItem
import zhihudaily.lqs.com.zhihu.adapter.RecyclerAdapter
import zhihudaily.lqs.com.zhihu.model.vo.StoryVo
import zhihudaily.lqs.com.zhihu.presenter.impl.DailyFragmentPresenter
import kotlinx.android.synthetic.main.daily_list_item.view.*
import kotlinx.android.synthetic.main.daily_list_item_banner.view.*
import zhihudaily.lqs.com.zhihu.application.MyApp
import zhihudaily.lqs.com.zhihu.model.vo.TopStorysVo
import zhihudaily.lqs.com.zhihu.utils.getLayoutInflater
import zhihudaily.lqs.com.zhihu.utils.startAct
import zhihudaily.lqs.com.zhihu.view.interfac.IDailyFragmentView
import java.util.*

/**
 * Created by admin on 2017/8/29.
 */
class DailyFragment : AbsFragment<DailyFragmentPresenter>(), IDailyFragmentView {

    companion object {
        val DATE_KET = "date_ket"
    }

    override val layoutRse: Int = R.layout.fragment_daily

    override val presenter: DailyFragmentPresenter by lazy { DailyFragmentPresenter().apply { view = this@DailyFragment } }

    val rvDailyListData: ArrayList<IItem> = ArrayList<IItem>()

    override fun initUi(view: View?) {
        rvDailyList.layoutManager = LinearLayoutManager(context)
        val recyclerAdapter = RecyclerAdapter(rvDailyListData)
        recyclerAdapter.registerView(StoryVo.TYPE, R.layout.daily_list_item, { data: IItem, view: View ->
            val storyVo = data as StoryVo
            with(storyVo) {
                view.icon?.setImageURI(images)
                view.title.apply {
                    text = this@with.title
                }
            }
        }, { data: IItem, view: View, posiction: Int ->
            val bundle = Bundle()
            var pos = posiction
            if(rvDailyListData[0].itemType == TopStorysVo.TYPE) pos--
            bundle.putSerializable(DatailyActivity.DATE_KEY,arguments.getSerializable(DATE_KET))
            bundle.putInt(DatailyActivity.POSITION_KEY,pos)
            MyApp.instance.startAct<DatailyActivity>(bundle)
        })
        recyclerAdapter.registerView(TopStorysVo.TYPE, R.layout.daily_list_item_banner, { data: IItem, view: View ->
            val topStorysVo = data as TopStorysVo
            with(topStorysVo) {
                if (adatpter == null) {
                    adatpter = object : PagerAdapter() {
                        override fun instantiateItem(container: ViewGroup?, position: Int): Any {
                            val layoutInflater = MyApp.instance.getLayoutInflater()
                            val inflate = layoutInflater.inflate(R.layout.banner_item, null)
                            inflate.apply {
                                bannerImaget.setImageURI(storyList[position % storyList.size].images)
                                bannerTitle.text = storyList[position % storyList.size].title
                            }
                            container?.addView(inflate)
                            return inflate
                        }

                        override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
                            container?.removeView(`object` as View)
                        }

                        override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
                            return view == `object`
                        }

                        override fun getCount(): Int {
                            return 10000;
                        }
                    }
                }
                view.banner?.adapter = adatpter
            }
        }, { data: IItem, view: View, posiction: Int ->

        })
        rvDailyList.adapter = recyclerAdapter

        val callback = object :ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT,ItemTouchHelper.LEFT){
            override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
                Log.e("direction",direction.toString())
                rvDailyListData.removeAt(viewHolder!!.adapterPosition)
                rvDailyList.adapter.notifyItemRemoved(viewHolder!!.adapterPosition)
            }
        }

        callback.setDefaultSwipeDirs(50000)

//        ItemTouchHelper(callback).attachToRecyclerView(rvDailyList)
    }

    override fun initData() {
        presenter.replaceData(arguments.getSerializable(DATE_KET) as Date)
    }

    override fun notifyDataChange(list: MutableList<IItem>) {
        rvDailyListData.addAll(list);
        rvDailyList.adapter.notifyDataSetChanged()
    }
}