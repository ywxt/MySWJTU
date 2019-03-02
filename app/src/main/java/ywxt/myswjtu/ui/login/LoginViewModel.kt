package ywxt.myswjtu.ui.login

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.media.Image
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.kodein.di.Kodein
import org.kodein.di.android.support.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton
import ywxt.myswjtu.common.viewmodels.BaseViewModel
import ywxt.myswjtu.checkers.LoginChecker
import ywxt.myswjtu.common.exceptions.LoginException
import ywxt.myswjtu.managers.UserManager

class LoginViewModel(application: Application) : BaseViewModel(application) {
    override val kodein: Kodein = parentKodein

    private val loginChecker by instance<LoginChecker>()
    private val userManager by instance<UserManager>()

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
        if (!checkUsername() || !checkPassword() ||!checkVerifyCode()) return
        Log.i("LoginViewModel", "login")
        userManager.login(username.value!!, password.value!!, verifyCode.value!!)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("Login.OnNext", it.getOrDefault(""))
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

    

}