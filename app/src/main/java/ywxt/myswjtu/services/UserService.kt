package ywxt.myswjtu.services

import io.reactivex.Flowable
import okhttp3.ResponseBody
import org.intellij.lang.annotations.Flow
import retrofit2.http.GET
import ywxt.myswjtu.models.UserAccountInfoModel

interface UserService {
    
    @GET("UserInfoSetAction?setAction=queryUserAccountInfo")
    fun getUserAccountInfo():Flowable<UserAccountInfoModel>
    
    @GET("UserFramework")
    fun getUser():Flowable<ResponseBody>
    
    @GET("StudentInfoAction?setAction=studentInfoQuery")
    fun getStudent():Flowable<ResponseBody>
    @GET("StudentInfoAction?setAction=studentParentInfo")
    fun getStuentParents():Flowable<ResponseBody>
}