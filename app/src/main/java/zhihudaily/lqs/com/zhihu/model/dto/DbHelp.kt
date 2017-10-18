package zhihudaily.lqs.com.zhihu.model.dto

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import org.jetbrains.anko.db.*
import zhihudaily.lqs.com.zhihu.application.MyApp

/**
 * Created by admin on 2017/10/17.
 */
class DbHelp(ctx: Context = MyApp.instance) : ManagedSQLiteOpenHelper(ctx, NAME, null, VERSION) {

    companion object {
        val VERSION = 1
        val NAME = "zhihuDb"
        val instance: DbHelp by lazy { DbHelp() }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
                StoryTable.NAME,
                true,
                StoryTable.ID to INTEGER + PRIMARY_KEY,
                StoryTable.TITLE to TEXT,
                StoryTable.DATE to TEXT,
                StoryTable.IMAGE to TEXT,
                StoryTable.GA_PREFIX to TEXT
        )

        db?.createTable(
                NewsTable.NAME,
                true,
                NewsTable.ID to INTEGER + PRIMARY_KEY,
                NewsTable.TITLE to TEXT,
                NewsTable.TYPE to INTEGER,
                NewsTable.IMAGE to TEXT,
                NewsTable.HTML to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}

object StoryTable {
    val NAME = "t_story"
    val IMAGE = "image"
    val ID = "_id"
    val GA_PREFIX = "ga_prefix"
    val TITLE = "title"
    val DATE = "date"
}

object NewsTable {
    val NAME = "t_news"
    val HTML = "html"
    val TITLE = "title"
    val IMAGE = "image"
    val TYPE = "type"
    val ID = "_id"
}