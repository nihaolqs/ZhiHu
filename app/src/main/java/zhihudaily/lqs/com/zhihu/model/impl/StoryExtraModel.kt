package zhihudaily.lqs.com.zhihu.model.impl

import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import zhihudaily.lqs.com.zhihu.connection.IConnection
import zhihudaily.lqs.com.zhihu.connection.IConnection.Companion.HOST
import zhihudaily.lqs.com.zhihu.model.dto.Extra
import zhihudaily.lqs.com.zhihu.model.interfac.IModel
import zhihudaily.lqs.com.zhihu.model.interfac.IStoryExtraModel
import zhihudaily.lqs.com.zhihu.presenter.interfac.IStoryExtraPresenter
import java.net.URL

/**
 * Created by admin on 2017/10/23.
 */
class StoryExtraModel(override var presenter: IStoryExtraPresenter) : IStoryExtraModel, IConnection<Extra>, IModel<IStoryExtraPresenter> {

    companion object {
        val STORY_EXTRA_URL = HOST + "story-extra/"
    }

    var mStoryId = 0L

    override fun getData(storyId: Long) {
        mStoryId = storyId
        doAsync {
            val extra = execute()
            uiThread {
                presenter.replaceViewData(extra)
            }
        }
    }

    override fun execute(): Extra {
        val jsonString = URL(STORY_EXTRA_URL + mStoryId).readText()
        val extra = Gson().fromJson<Extra>(jsonString, Extra::class.java)
        return extra
    }

}