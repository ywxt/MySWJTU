package ywxt.myswjtu

import android.app.Application
import android.content.pm.ApplicationInfo
import com.alibaba.android.arouter.launcher.ARouter
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidCoreModule
import org.kodein.di.android.support.androidSupportModule
import ywxt.myswjtu.modules.HttpModule
import ywxt.myswjtu.modules.checkerModule
import ywxt.myswjtu.modules.routerModule
import ywxt.myswjtu.modules.serviceModule


class App : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        //bind<Context>() with singleton { this@App }
        import(androidCoreModule(this@App))
        import(androidSupportModule(this@App))
        import(serviceModule)
        import(HttpModule)
        import(checkerModule)
        import(routerModule)
    }

    override fun onCreate() {
        super.onCreate()
        if(isApkInDebug()){
            ARouter.openLog() 
            ARouter.openDebug() 
        }
        ARouter.init(this)
    }

    private fun isApkInDebug(): Boolean {
        return try {
            val info = this.applicationInfo
            info.flags and  ApplicationInfo.FLAG_DEBUGGABLE != 0
        } catch (e: Exception) {
            false
        }

    }
}