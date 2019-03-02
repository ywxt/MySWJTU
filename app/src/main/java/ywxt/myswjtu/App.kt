package ywxt.myswjtu

import android.app.Application
import android.content.Context
import android.util.Log
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidCoreModule
import org.kodein.di.android.support.androidSupportModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import ywxt.myswjtu.modules.HttpModule
import ywxt.myswjtu.modules.checkerModule
import ywxt.myswjtu.modules.serviceModule

class App : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        //bind<Context>() with singleton { this@App }
        import(androidCoreModule(this@App))
        import(androidSupportModule(this@App))
        import(serviceModule)
        import(HttpModule)
        import(checkerModule)
    }

    override fun onCreate() {
        INSTANCE = this
        super.onCreate()
        Log.i("App","App Created")
    }

    companion object {
        lateinit var INSTANCE: App
    }
}