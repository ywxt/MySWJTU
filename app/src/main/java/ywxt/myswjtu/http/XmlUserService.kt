package ywxt.myswjtu.http

import io.reactivex.Flowable
import retrofit2.http.GET
import ywxt.myswjtu.models.UserAccountInfoModel
import ywxt.myswjtu.models.UserInfoModel

interface XmlUserService {
    
    @GET("UserInfoSetAction?setAction=queryUserAccountInfo")
    fun getUserAccountInfo():Flowable<UserAccountInfoModel>
    
   
    
}
interface HtmlUserService{
    @GET("UserFramework")
    fun getUserInfo():Flowable<UserInfoModel>
}