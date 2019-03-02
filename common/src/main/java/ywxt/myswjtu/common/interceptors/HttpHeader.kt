package ywxt.myswjtu.common.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class HttpHeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest=chain.request()
        val newRequest=oldRequest.newBuilder()
            .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
            .build()
        return chain.proceed(newRequest)
    }
}