package zhihudaily.lqs.com.zhihu.adapter

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import kotlin.properties.Delegates
import android.animation.ObjectAnimator


@RequiresApi(Build.VERSION_CODES.N)
/**
 * Created by admin on 2017/9/28.
 */
class RecyclerSwipeItemLayout(context: Context) : FrameLayout(context) {
    val leftMenuLayout: LinearLayout by lazy {
        LinearLayout(context).apply {
            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT)
            gravity = Gravity.LEFT
            orientation = LinearLayout.HORIZONTAL
            visibility = View.GONE
        }
    }
    val rightMenuLayout: LinearLayout by lazy {
        LinearLayout(context).apply {
            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT)
            gravity = Gravity.LEFT
            orientation = LinearLayout.HORIZONTAL
            visibility = View.GONE
        }
    }

    private var mRootView: View by Delegates.notNull<View>()

    init {
        this.addView(leftMenuLayout)
        this.addView(rightMenuLayout)
        layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        clipChildren = false
    }

    fun addLeftMenu(view: View) {
        view.parent?.apply { (parent as ViewGroup).removeView(view) }
        leftMenuLayout.addView(view)
        leftMenuLayout.visibility = View.VISIBLE
    }

    fun addRightMenu(view: View) {
        view.parent?.apply { (parent as ViewGroup).removeView(view) }
        rightMenuLayout.addView(view)
        rightMenuLayout.visibility = View.VISIBLE
    }

    fun addRootView(view: View) {
        view.parent?.apply {
            (parent as ViewGroup).removeView(view)
        }
        addView(view)
        mRootView = view
        mRootView.apply {
            layoutParams = FrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        }
    }

    private var mX = 0;
    private var isSwipel = false
    private var mTotalSwipel = 0
    private var mHistorySwipel = 0

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            mX = event.x.toInt()
            Log.e("onTouchEvent:mx", mX.toString())
            if (mX < 80 || mX > width - 80) {
                parent.requestDisallowInterceptTouchEvent(true)
                isSwipel = true;
            } else {
                parent.requestDisallowInterceptTouchEvent(false)
                isSwipel = false
            }
            mRootView.clearAnimation()
        }
        Log.e("measuredWidth", leftMenuLayout.measuredWidth.toString())
        Log.e("Width", leftMenuLayout.width.toString())
        if (isSwipel && event?.action == MotionEvent.ACTION_MOVE) {
            swipel(event)
        }

        if (event?.action == MotionEvent.ACTION_UP) {
            isClickable = !isSwipel
            if (isSwipel) {
                val animator = ObjectAnimator.ofFloat(mRootView, "translationX", -mTotalSwipel.toFloat(), 0f)
                animator.setAutoCancel(true)
                animator.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        isClickable = true
                        animator.cancel()
                        Log.e("onAnimationEnd", "onAnimationEnd")
                    }
                })
                animator.duration = 500
                mRootView.scrollBy(-mTotalSwipel, 0)
                animator.start()
                mHistorySwipel = mTotalSwipel
                mTotalSwipel = 0
            }
        }
        return super.onTouchEvent(event)
    }

    private fun swipel(event: MotionEvent) {
        var x = mX - event.x.toInt()
        Log.e("X", x.toString())
        if ((mTotalSwipel <= 0 && Math.abs(mTotalSwipel) < leftMenuLayout.measuredWidth) || (mTotalSwipel >= 0 && Math.abs(mTotalSwipel) < rightMenuLayout.measuredWidth)) {
            mRootView.scrollBy(x, 0)
            mTotalSwipel += x
            mX = event.x.toInt()
        } else {
            if (mTotalSwipel < 0) mLeftSwipeListener?.invoke(leftMenuLayout, mRootView) else mRightSwipeListener?.invoke(rightMenuLayout, mRootView)
        }
    }

    var mLeftSwipeListener: ((menuViewLayout: ViewGroup, rootView: View) -> Unit)? = null
    fun setOnLeftSwipelMenuListener(leftSwipelListener: (menuView: View, rootView: View) -> Unit) {
        mLeftSwipeListener = leftSwipelListener
    }

    var mRightSwipeListener: ((menuViewLayout: ViewGroup, rootView: View) -> Unit)? = null
    fun setOnRightSwipelMenuListener(rightSwipelListener: (menuView: View, rootView: View) -> Unit) {
        mRightSwipeListener = rightSwipelListener
    }

}