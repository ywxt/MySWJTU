package ywxt.myswjtu.ui.splash

import android.content.Intent
import com.alibaba.android.arouter.launcher.ARouter
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import ywxt.myswjtu.common.ui.BaseActivity
import ywxt.myswjtu.common.viewmodels.BaseActivityViewModel
import ywxt.myswjtu.managers.CookieManager
import ywxt.myswjtu.managers.UserManager
import ywxt.myswjtu.modules.PATH_ROUTE_MAIN

class SplashViewModel(activity: BaseActivity) : BaseActivityViewModel(activity) {

    private val router: ARouter by instance()
    private val cookieManager: CookieManager by instance()
    private val userManager: UserManager by instance()

    fun navigate2Main() {
        val loadCookie = cookieManager.loadCookie()
        val getUser = userManager.getUser()
        Flowable.concat(loadCookie, getUser)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                router.build(PATH_ROUTE_MAIN)
                    .withFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    .navigation()
            }
            .doOnError {
                router.build(PATH_ROUTE_MAIN)
                    .withFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    .navigation()
            }
            .subscribe({}, {})
    }
}