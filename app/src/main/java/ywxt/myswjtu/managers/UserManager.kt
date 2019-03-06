package ywxt.myswjtu.managers

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import ywxt.myswjtu.common.exceptions.LoginException
import ywxt.myswjtu.common.exceptions.ParamInitException
import ywxt.myswjtu.models.LoginInitModel
import ywxt.myswjtu.models.UserAccountInfoModel
import ywxt.myswjtu.models.UserInfoModel
import ywxt.myswjtu.models.UserModel
import ywxt.myswjtu.services.LoginService
import ywxt.myswjtu.services.UserService

class UserManager(
    private val loginService: LoginService,
    private val userService: UserService,
    private val toastManager: ToastManager
) {

    var signed = false

    val user: UserModel = UserModel(
        MutableLiveData(),
        MutableLiveData(),
        MutableLiveData(),
        MutableLiveData(),
        MutableLiveData(),
        MutableLiveData(),
        ""
    )

    fun login(username: String, password: String, verification: String): Flowable<Result<String>> {
        return loginService.login(username, password, verification)
            .subscribeOn(Schedulers.io())
            .map {
                //Log.i("UserManager", "Login${it.loginStatus}")
                when (it.loginStatus) {
                    1 -> Result.success(it.loginMsg)
                    else -> Result.failure(LoginException(it.loginMsg, it.loginStatus))
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                if (it.isFailure) throw it.exceptionOrNull()!!
                signed = true
            }.doOnError {
                if (it?.message != null)
                    toastManager.toast(it.message!!)
            }
    }
    
    fun getVerifyImage(): Flowable<Bitmap> {
        return loginService.getVerifyImage()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map {
                val bitmap = BitmapFactory.decodeStream(it.byteStream())
                it.close()
                bitmap
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                if (it?.message != null)
                    toastManager.toast(it.message!!)
            }
    }

    fun initLogin(): Flowable<LoginInitModel> {
        return loginService.init()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                if (it.success != 1) throw ParamInitException("登录参数初始化异常")
            }
    }

    fun getUser(): Flowable<UserModel> {
        return Flowable.zip(
            userService.getUserInfo(),
            userService.getUserAccountInfo(),
            BiFunction<UserInfoModel, UserAccountInfoModel, UserModel> { userInfoModel, userAccountInfoModel ->
                user.apply {
                    number.postValue(userInfoModel.number)
                    name.postValue(userInfoModel.name)
                    image.postValue(userAccountInfoModel.userImg)
                    email.postValue(userAccountInfoModel.email)
                    qq.postValue(userAccountInfoModel.qq)
                    mobilePhone.postValue(userAccountInfoModel.mobilePhone)
                }
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                if (it?.message != null)
                    toastManager.toast(it.message!!)
            }
    }

}