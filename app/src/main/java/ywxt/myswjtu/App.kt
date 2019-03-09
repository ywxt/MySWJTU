package ywxt.myswjtu

import android.app.Application
import android.content.pm.ApplicationInfo
import com.alibaba.android.arouter.launcher.ARouter
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidCoreModule
import org.kodein.di.android.x.androidXModule
import ywxt.myswjtu.modules.*


class App : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
       // bind<Context>() with singleton { this@App }
        import(androidCoreModule(this@App))
        import(androidXModule(this@App))
        import(notificationModule)
        import(storageModule)
        import(userModule)
        import(HttpModule)
        import(checkerModule)
        import(routerModule)
    }

//    override fun attachBaseContext(base: Context?) {
//        super.attachBaseContext(base)
//        MultiDex.install(this)
//    }
    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        if (isApkInDebug()) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }

    private fun isApkInDebug(): Boolean = try {
        val info = this.applicationInfo
        info.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
    } catch (e: Exception) {
        false
    }

    companion object {
        lateinit var INSTANCE: App
    }
}