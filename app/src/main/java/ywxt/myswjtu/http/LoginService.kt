package ywxt.myswjtu.http

import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.http.*
import ywxt.myswjtu.models.LoginInitModel
import ywxt.myswjtu.models.LoginModel


interface LoginService {
    /**
     * 登录
     */
    @POST("UserLoginAction")
    @FormUrlEncoded
    @Headers("Referer: https://jwc.swjtu.edu.cn/service/login.html")
    fun login(
        @Field("username") username: String, 
        @Field("password") password: String,
        @Field("ranstring")verifyCode:String
    ):Flowable<LoginModel>

    /**
     * 获取验证码
     */
    @GET("GetRandomNumberToJPEG")
    @Streaming
    fun getVerifyImage():Flowable<ResponseBody>

    /**
     * 初始化
     */
    @GET("StartCaptchaServlet")
    fun  init():Flowable<LoginInitModel>
}