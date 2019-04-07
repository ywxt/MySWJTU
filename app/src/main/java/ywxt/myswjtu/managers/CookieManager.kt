package ywxt.myswjtu.managers

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import ywxt.myswjtu.common.BASE_URL
import ywxt.myswjtu.common.typeadapters.CookieAdapter

class CookieManager(
    private val configurationManager: ConfigurationManager,
    private val cookieJar: CookieJar
) {
    private val gson = GsonBuilder().registerTypeAdapter(Cookie::class.java, CookieAdapter()).create()

    fun loadCookie(): Flowable<List<Cookie>> {

        return configurationManager.getCookie().observeOn(Schedulers.computation())
            .map {
                gson.fromJson<List<Cookie>>(it, object : TypeToken<List<Cookie>>() {}.type)
            }.doOnNext {
                cookieJar.saveFromResponse(HttpUrl.get(BASE_URL), it)
            }

    }

    fun saveCookie() {
        val cookie = cookieJar.loadForRequest(HttpUrl.get(BASE_URL))
        configurationManager.writeCookie(gson.toJson(cookie, object : TypeToken<List<Cookie>>() {}.type))
    }
}