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
import ywxt.myswjtu.http.CourseService
import ywxt.myswjtu.http.HtmlUserService
import ywxt.myswjtu.http.LoginService
import ywxt.myswjtu.http.XmlUserService
import ywxt.myswjtu.models.*

class UserManager(
    private val loginService: LoginService,
    private val xmlUserService: XmlUserService,
    private val htmlUserService: HtmlUserService
) {

    var signed = false

    lateinit var user: UserModel

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
            htmlUserService.getUserInfo(),
            xmlUserService.getUserAccountInfo(),
            BiFunction<UserInfoModel, UserAccountInfoModel, UserModel> { userInfoModel, userAccountInfoModel ->
                user = UserModel(
                    number = userInfoModel.number,
                    name = userInfoModel.name,
                    image = userAccountInfoModel.userImg,
                    email = userAccountInfoModel.email,
                    qq = userAccountInfoModel.qq,
                    mobilePhone = userAccountInfoModel.mobilePhone,
                    cookie = ""
                )
                user
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadingAfterLogin(): Flowable<Boolean> {
        return loginService.loadingAfterLogin(null, null, null)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { true }

    }


}