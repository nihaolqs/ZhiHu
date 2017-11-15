package zhihudaily.lqs.com.zhihu.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.SelectQueryBuilder
import zhihudaily.lqs.com.zhihu.model.vo.StoryVo
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by admin on 2017/8/29.
 */

public fun Context.getLayoutInflater() = LayoutInflater.from(this)

fun Date.toFormatLongDay() = SimpleDateFormat("yyyyMMdd").format(this).toLong()

fun Date.toFormatDay() = SimpleDateFormat("MM-dd HH:mm").format(this)

inline fun <T> T.fromFormatLongDay(date: Long): Date = SimpleDateFormat("yyyyMMdd").parse(date.toString())

operator fun Date.minus(i: Int): Date {
    time = time - i * 24 * 60 * 60 * 1000L
    return this
}

fun Context.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, text, duration).show()

inline fun <reified T : Activity> Context.startAct() {
    startActivity(Intent(this, T::class.java))
}

inline fun <reified T : Activity> Context.startAct(bundle: Bundle) {
    val intent = Intent(this, T::class.java)
    intent.putExtras(bundle)
    startActivity(intent)
}

/**
 * 数据库查询拓展工具方法
 */
fun <T : Any> SelectQueryBuilder.parseOpt(function: (Map<String, Any>) -> T): T? =
        this.parseOpt(object : MapRowParser<T> {
            override fun parseRow(columns: Map<String, Any>): T = function(columns)
        })

fun <T : Any> SelectQueryBuilder.parseList(function: (Map<String, Any>) -> T): List<T> =
        this.parseList(object : MapRowParser<T> {
            override fun parseRow(columns: Map<String, Any>): T = function(columns)
        })


inline fun <T, R> Iterable<T>.firstResult(predicate: (T) -> R): R {
    for (element in this) {
        val result = predicate(element)
        if (result != null) return result
    }
    throw NoSuchElementException("No element matching predicate was found.")
}

inline fun <T,R:Any> MutableMap<T,R>.toVararyArray(): Array<Pair<T, R>> = this.map{it.key to it.value}.toTypedArray()

