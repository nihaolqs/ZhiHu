package zhihudaily.lqs.com.zhihu.adapter

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by admin on 2017/8/30.
 */
class RecyclerViewHolder(itemView: View, val initUi: (data: IItem, view:View) -> Unit,
                         val itemClick: (data: IItem, view: View, posiction: Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
}