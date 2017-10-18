package zhihudaily.lqs.com.zhihu.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by admin on 2017/8/29.
 */

public fun Context.getLayoutInflater() = LayoutInflater.from(this)

fun Date.toFormatLongDay() = SimpleDateFormat("yyyyMMdd").format(this).toLong()

inline fun<T> T.fromFormatLongDay(date:Long):Date = SimpleDateFormat("yyyyMMdd").parse(date.toString())

operator fun Date.minus(i: Int):Date{
    time  = time - i*24*60*60*1000L
    return this
}

fun Context.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, text, duration).show()

inline fun<reified T:Activity> Context.startAct(){
    startActivity(Intent(this,T::class.java))
}

inline fun<reified T:Activity> Context.startAct(bundle: Bundle){
    val intent = Intent(this, T::class.java)
    intent.putExtras(bundle)
    startActivity(intent)
}