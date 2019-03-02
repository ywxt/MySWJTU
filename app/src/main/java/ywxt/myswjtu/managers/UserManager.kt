package ywxt.myswjtu.managers

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ywxt.myswjtu.common.exceptions.LoginException
import ywxt.myswjtu.common.exceptions.ParamInitException
import ywxt.myswjtu.models.LoginInitModel
import ywxt.myswjtu.services.LoginService

class UserManager(private val loginService: LoginService) {

    var isSigned = false

    fun login(username: String, password: String, verification: String): Flowable<Result<String>> =
        loginService.login(username, password, verification)
            .subscribeOn(Schedulers.io())
            .map {
                //Log.i("UserManager", "Login${it.loginStatus}")
                when (it.loginStatus) {
                    1 -> Result.success(it.loginMsg)
                    else -> Result.failure(LoginException(it.loginMsg, it.loginStatus))
                }
            }.subscribeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                if (it.isFailure) throw it.exceptionOrNull()!!
            }


    fun getVerifyImage(): Flowable<Bitmap> =
        loginService.getVerifyImage()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map {
                val bitmap=BitmapFactory.decodeStream(it.byteStream())
                it.close()
                bitmap
            }
    fun initLogin():Flowable<LoginInitModel> = 
            loginService.init()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { 
                    if (it.success!=1) throw ParamInitException("登录参数初始化异常")
                }
}