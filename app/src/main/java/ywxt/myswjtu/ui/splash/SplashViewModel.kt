package ywxt.myswjtu.ui.splash

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import ywxt.myswjtu.common.viewmodels.BaseViewModel
import ywxt.myswjtu.modules.PATH_ROUTE_MAIN

class SplashViewModel(application: Application):BaseViewModel(application) {
    override val kodein: Kodein = parentKodein
    private val router:ARouter by instance()
    fun navigate2Main(){
        router.build(PATH_ROUTE_MAIN).navigation()
    }
}