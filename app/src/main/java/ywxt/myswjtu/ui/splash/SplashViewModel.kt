package ywxt.myswjtu.ui.splash

import android.content.Intent
import com.alibaba.android.arouter.launcher.ARouter
import ywxt.myswjtu.common.ui.BaseActivity
import ywxt.myswjtu.common.viewmodels.BaseActivityViewModel
import ywxt.myswjtu.modules.PATH_ROUTE_MAIN

class SplashViewModel(activity: BaseActivity) : BaseActivityViewModel(activity) {

    private val router: ARouter by instance()

    fun navigate2Main() {
        router.build(PATH_ROUTE_MAIN)
            .withFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            .navigation()
    }
}