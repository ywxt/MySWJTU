package ywxt.myswjtu.ui.splash

import android.content.Intent
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import ywxt.myswjtu.common.ui.BaseActivity
import ywxt.myswjtu.common.viewmodels.BaseActivityViewModel
import ywxt.myswjtu.managers.CookieManager
import ywxt.myswjtu.managers.ToastManager
import ywxt.myswjtu.managers.UserManager
import ywxt.myswjtu.modules.PATH_ROUTE_MAIN
import java.net.SocketTimeoutException

class SplashViewModel(activity: BaseActivity) : BaseActivityViewModel(activity) {

    private val router: ARouter by instance()
    private val cookieManager: CookieManager by instance()
    private val userManager: UserManager by instance()
    private val toastManager:ToastManager by instance()

    init {
        navigate2Main()
    }
    
    fun navigate2Main() {
        Flowable.concat(cookieManager.loadCookie(), userManager.isValidUser(), userManager.getUser())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                router.build(PATH_ROUTE_MAIN)
                    .withFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    .navigation()
            }.doOnComplete {
                    router.build(PATH_ROUTE_MAIN)
                        .withFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        .navigation()

            }.doOnCancel {
                Log.i("InitUser", "Cancel")
            }.doOnTerminate {
                Log.i("InitUser", "doOnTerminate")
            }
            .subscribe({},{
                when(it){
                    is SocketTimeoutException -> {
                        toastManager.toast("连接教务网失败，请检查网络状况")
                    }
                }
            })
    }
}