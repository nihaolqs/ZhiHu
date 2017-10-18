package zhihudaily.lqs.com.zhihu.adapter

import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import zhihudaily.lqs.com.zhihu.application.MyApp
import zhihudaily.lqs.com.zhihu.utils.getLayoutInflater


/**
 * Created by admin on 2017/8/30.
 */
class RecyclerAdapter(val recyclerDataList: List<IItem>) : RecyclerView.Adapter<RecyclerViewHolder>() {

    val typeMap = HashMap<Int, Triple<Int, (IItem, View) -> Unit, (IItem, View, Int) -> Unit>>()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerViewHolder {
        val triple = typeMap.get(viewType)!!
        val inflater = MyApp.instance.getLayoutInflater()
        val view = inflater.inflate(triple?.first!!, parent, false)
        val swichView = RecyclerSwipeItemLayout(MyApp.instance)
        swichView.addLeftMenu(TextView(MyApp.instance).apply {
            text = "menu"
        })
//        swichView.addRightMenu(TextView(MyApp.instance).apply {
//            text = "menu"
//        })
        swichView.addRootView(view)
        return RecyclerViewHolder(swichView, triple.second, triple.third).apply {
            itemView.setOnClickListener {
                itemClick(recyclerDataList[this.adapterPosition], itemView, this.adapterPosition)
//                Log.e("posiction",this.adapterPosition.toString())
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerViewHolder?, position: Int) {
        holder?.apply {
            initUi(recyclerDataList[position], itemView)
        }
    }

    override fun getItemCount(): Int = recyclerDataList.size

    override fun getItemViewType(position: Int): Int = recyclerDataList[position].itemType

    /**
     * 注册行类型
     */
    fun registerView(type: Int, itemRes: Int, initUI: (data: IItem, view: View) -> Unit,
                     itemClick: (data: IItem, view: View, posiction: Int) -> Unit) = typeMap.put(type, Triple(itemRes, initUI, itemClick))

}