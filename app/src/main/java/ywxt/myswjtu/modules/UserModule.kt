package ywxt.myswjtu.modules

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import ywxt.myswjtu.managers.UserManager
import ywxt.myswjtu.http.LoginService
import ywxt.myswjtu.http.UserService

const val USER_MODULE_NAME="USER_MODULE_NAME"
val  userModule =Kodein.Module(USER_MODULE_NAME){
    bind<LoginService>() with singleton { instance<Retrofit>().create(LoginService::class.java) }
    bind<UserService>() with singleton { instance<Retrofit>().create(UserService::class.java) }
    bind<UserManager>() with singleton { UserManager(instance(),instance(),instance()) }
}
