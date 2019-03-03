package ywxt.myswjtu.ui.login

import android.app.Application
import android.content.Intent
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.launcher.ARouter
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import ywxt.myswjtu.checkers.LoginChecker
import ywxt.myswjtu.common.exceptions.LoginException
import ywxt.myswjtu.common.viewmodels.BaseViewModel
import ywxt.myswjtu.managers.UserManager
import ywxt.myswjtu.modules.PATH_ROUTE_MAIN

class LoginViewModel(application: Application) : BaseViewModel(application) {
    override val kodein: Kodein = parentKodein

    private val loginChecker by instance<LoginChecker>()
    private val userManager by instance<UserManager>()
    private val router: ARouter by instance()

    val username: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val verifyCode: MutableLiveData<String> = MutableLiveData()
    val verifyImage: MutableLiveData<Bitmap> = MutableLiveData()
    val usernameErrorMessage: MutableLiveData<String> = MutableLiveData()
    val passwordErrorMessage: MutableLiveData<String> = MutableLiveData()
    val verifyCodeErrorMessage: MutableLiveData<String> = MutableLiveData()

    init {
        //初始化
        Flowable.concat(
            userManager.initLogin(),
            userManager.getVerifyImage()
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {

                    Log.i("Login.Init", "success")
                    if (it is Bitmap) verifyImage.value = it
                },
                {
                    Log.i("Login.Init", it.message)
                }
            )
    }

    fun login() {
        if (!checkUsername() || !checkPassword() || !checkVerifyCode()) return
        Log.i("LoginViewModel", "login")
        userManager.login(username.value!!, password.value!!, verifyCode.value!!)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("Login.OnNext", it.getOrDefault(""))
                navigate2Main()
            }, {
                if (it is LoginException)
                    Log.i("Login.OnError", "${it.message}   ${it.code}")
            })
    }

    fun checkUsername(): Boolean {
        val user = username.value
        val errorMessage = loginChecker.checkUserName(user)
        //Log.i("LoginViewModel", errorMessage + "")
        usernameErrorMessage.value = errorMessage
        return errorMessage == null
    }

    fun checkPassword(): Boolean {
        val pw = password.value
        val errorMessage = loginChecker.checkPassword(pw)
        //Log.i("LoginViewModel", errorMessage + "")
        passwordErrorMessage.value = errorMessage
        return errorMessage == null
    }

    fun checkVerifyCode(): Boolean {
        val code = verifyCode.value
        val errorMessage = loginChecker.checkVerifyCode(code)
        //Log.i("LoginViewModel", errorMessage + "")
        verifyCodeErrorMessage.value = errorMessage
        return errorMessage == null
    }

    fun getVerifyImages() {
        Log.i("VerifyImage", "get")
        userManager.getVerifyImage()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    verifyImage.value = it
                    Log.i("VerifyImage", "susscess")
                }
                ,
                {
                    // TODO
                }
            )

    }
    
    private fun navigate2Main(){
        router.build(PATH_ROUTE_MAIN)
            .withFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            .navigation()
    }

}