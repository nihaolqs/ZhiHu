package zhihudaily.lqs.com.zhihu.adapter

import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import zhihudaily.lqs.com.zhihu.application.MyApp
import zhihudaily.lqs.com.zhihu.utils.getLayoutInflater


/**
 * Created by admin on 2017/8/30.
 */
class RecyclerAdapter(val recyclerDataList: List<IItem>) : RecyclerView.Adapter<RecyclerViewHolder>() {

    val typeMap = HashMap<Int, Triple<Int, (IItem, View) -> Unit, (IItem, View, Int) -> Unit>>()
    val swipeMenuMap = HashMap<Int, Pair<Triple<Int, (data: IItem, view: View) -> Unit, (data: IItem, menuview: View, rootView: View, posiction: Int) -> Unit>?,
            Triple<Int, (data: IItem, view: View) -> Unit, (data: IItem, menuview: View, rootView: View, posiction: Int) -> Unit>?>>()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerViewHolder {
        val triple = typeMap.get(viewType)!!
        val inflater = MyApp.instance.getLayoutInflater()
        val view = inflater.inflate(triple?.first!!, parent, false)
        val swichView = RecyclerSwipeItemLayout(MyApp.instance)
//        swichView.addLeftMenu(TextView(MyApp.instance).apply {
//            text = "menu"
//        })
//        swichView.addRightMenu(TextView(MyApp.instance).apply {
//            text = "menu"
//        })
        swipeMenuMap.get(viewType)?.apply {
            first?.apply {
                val nemuView = inflater.inflate(first, null)
                swichView.addLeftMenu(nemuView)
            }
            second?.apply {
                val nemuView = inflater.inflate(first, null)
                swichView.addRightMenu(nemuView)
            }
        }

        swichView.addRootView(view)
        return RecyclerViewHolder(swichView, triple.second, triple.third).apply {
            itemView.setOnClickListener {
                itemClick(recyclerDataList[this.adapterPosition], itemView, this.adapterPosition)
//                Log.e("posiction",this.adapterPosition.toString())
            }
            val itemLayout = this.itemView as RecyclerSwipeItemLayout
            swipeMenuMap.get(viewType)?.apply {
                first?.apply {
                    itemLayout.setOnLeftSwipelMenuListener { menuView, rootView -> this.third(recyclerDataList[adapterPosition], menuView, rootView, adapterPosition) }
                }
                second?.apply {
                    itemLayout.setOnRightSwipelMenuListener { menuView, rootView -> this.third(recyclerDataList[adapterPosition], menuView, rootView, adapterPosition) }
                }
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerViewHolder?, position: Int) {
        holder?.apply {
            initUi(recyclerDataList[position], itemView)
        }
        swipeMenuMap.get(getItemViewType(position))?.apply {
            first?.apply {
                second(recyclerDataList[position], (holder?.itemView!! as RecyclerSwipeItemLayout).leftMenuLayout)
            }
            second?.apply {
                second(recyclerDataList[position], (holder?.itemView!! as RecyclerSwipeItemLayout).rightMenuLayout)
            }
        }
    }

    override fun getItemCount(): Int = recyclerDataList.size

    override fun getItemViewType(position: Int): Int = recyclerDataList[position].itemType

    /**
     * 注册行类型
     */
    fun registerView(type: Int, itemRes: Int,
                     initUI: (data: IItem, view: View) -> Unit = { data: IItem, view: View -> },
                     itemClick: (data: IItem, view: View, posiction: Int) -> Unit = { data: IItem, view: View, posiction: Int -> })
            = typeMap.put(type, Triple(itemRes, initUI, itemClick))

    /**
     * 注册侧滑菜单
     */
    fun registerSwipelMenu(itemType: Int, menuType: RecyclerSwipeItemLayout.MenuType, itemRes: Int, initMenuUi: (data: IItem, view: View) -> Unit, menuListener: (data: IItem, menuview: View, rootView: View, posiction: Int) -> Unit) {
        val isContains = swipeMenuMap.contains(itemType)
        if (!isContains) {
            swipeMenuMap.put(itemType, Pair(null, null))
        }
        when (menuType) {
            RecyclerSwipeItemLayout.MenuType.LEFTNEMU -> swipeMenuMap.set(itemType, Triple(itemRes, initMenuUi, menuListener) to swipeMenuMap.get(itemType)!!.second)
            RecyclerSwipeItemLayout.MenuType.RIGHTMENU -> swipeMenuMap.set(itemType, swipeMenuMap.get(itemType)!!.first to Triple(itemRes, initMenuUi, menuListener))
        }
    }
}