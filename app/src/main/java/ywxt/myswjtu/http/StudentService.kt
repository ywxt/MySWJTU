package ywxt.myswjtu.http

import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.http.GET

interface StudentService {
    
    @GET("StudentInfoAction?setAction=studentInfoQuery")
    fun getStudent(): Flowable<ResponseBody>
    
    
    @GET("StudentInfoAction?setAction=studentParentInfo")
    fun getStuentParents(): Flowable<ResponseBody>
}