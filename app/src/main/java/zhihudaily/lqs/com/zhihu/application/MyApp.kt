package zhihudaily.lqs.com.zhihu.application

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import kotlin.properties.Delegates

/**
 * Created by admin on 2017/8/29.
 */
class MyApp :Application(){
    companion object {
        var instance :MyApp by Delegates.notNull<MyApp>()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this;
        Fresco.initialize(this);
    }
}