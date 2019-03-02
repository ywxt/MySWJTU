package ywxt.myswjtu.modules

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import ywxt.myswjtu.managers.ToastManager
import ywxt.myswjtu.services.LoginService
import ywxt.myswjtu.managers.UserManager

const val SERVICE_MODULE_NAME = "SERVICE_MODULE_NAME"
/**
 * Service 模块
 */
val serviceModule = Kodein.Module(SERVICE_MODULE_NAME) {
    bind<LoginService>() with singleton { instance<Retrofit>().create(LoginService::class.java) }
    bind<UserManager>() with singleton { UserManager(instance()) }
    bind<ToastManager>() with singleton { ToastManager(instance()) }
}