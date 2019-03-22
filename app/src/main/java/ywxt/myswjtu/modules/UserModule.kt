package ywxt.myswjtu.modules

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import ywxt.myswjtu.http.CourseService
import ywxt.myswjtu.http.HtmlUserService
import ywxt.myswjtu.managers.UserManager
import ywxt.myswjtu.http.LoginService
import ywxt.myswjtu.http.XmlUserService
import ywxt.myswjtu.managers.TimetableManager

const val USER_MODULE_NAME = "USER_MODULE_NAME"
val userModule = Kodein.Module(USER_MODULE_NAME) {
    bind<LoginService>() with singleton { instance<Retrofit>(RETROFIT_JSON).create(LoginService::class.java) }
    bind<XmlUserService>() with singleton { instance<Retrofit>(RETROFIT_XML).create(XmlUserService::class.java) }
    bind<CourseService>() with singleton { instance<Retrofit>(RETROFIT_HTML).create(CourseService::class.java) }
    bind<HtmlUserService>() with singleton { instance<Retrofit>(RETROFIT_HTML).create(HtmlUserService::class.java) }
    bind<UserManager>() with singleton { UserManager(instance(), instance(), instance(), instance()) }
    bind<TimetableManager>() with singleton { TimetableManager(instance()) }
}
