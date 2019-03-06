package ywxt.myswjtu.common.http

import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Streaming

interface ImageService {
    @GET("{path}")
    @Streaming
    fun getImage(
        @Path("path") path:String
    ):Flowable<ResponseBody>
}