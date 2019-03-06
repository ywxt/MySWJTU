package ywxt.myswjtu.http

import io.reactivex.Flowable
import retrofit2.http.GET
import ywxt.myswjtu.models.UserAccountInfoModel
import ywxt.myswjtu.models.UserInfoModel

interface UserService {
    
    @GET("UserInfoSetAction?setAction=queryUserAccountInfo")
    fun getUserAccountInfo():Flowable<UserAccountInfoModel>
    
    @GET("UserFramework")
    fun getUserInfo():Flowable<UserInfoModel>
    
}