package ywxt.myswjtu.modules

import android.content.Context
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import okhttp3.CookieJar
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.jaxb.JaxbConverterFactory
import ywxt.myswjtu.common.BASE_URL
import ywxt.myswjtu.common.INTENT_TIME_OUT
import java.util.concurrent.TimeUnit

const val HTTP_MODULE_NAME = "HTTP_MODULE_NAME"
/**
 * Http模块
 */
val HttpModule = Kodein.Module(HTTP_MODULE_NAME) {

    bind<Retrofit.Builder>() with provider { Retrofit.Builder() }

    bind<OkHttpClient.Builder>() with provider { OkHttpClient.Builder() }

    bind<CookieJar>() with singleton {
        PersistentCookieJar(
            SetCookieCache(),
            SharedPrefsCookiePersistor(instance<Context>())
        )
    }

    bind<OkHttpClient>() with singleton {
        instance<OkHttpClient.Builder>()
            .connectTimeout(INTENT_TIME_OUT, TimeUnit.MILLISECONDS)
            .readTimeout(INTENT_TIME_OUT, TimeUnit.MILLISECONDS)
            .cookieJar(instance())
            .build()
    }
    bind<Retrofit>() with singleton {
        instance<Retrofit.Builder>()
            .baseUrl(BASE_URL)
            .client(instance())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(JaxbConverterFactory.create())
            .build()
    }
}