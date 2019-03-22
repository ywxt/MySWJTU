package ywxt.myswjtu.modules

import com.alibaba.android.arouter.launcher.ARouter
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

const val ROUTER_MODULE_NAME="ROUTER_MODULE_NAME"
const val PATH_ROUTE_LOGIN="/login/activity"
const val PATH_ROUTE_MAIN="/main/main"
const val PATH_ROUTE_MAIN_MAIN="/main/main/main"
const val PATH_ROUTE_MAIN_ME="/main/main/me"
const val PATH_ROUTE_MAIN_NOTIFICATION="/main/main/notification"
const val PATH_ROUTE_MAIN_TIMETABLE="/main/main/timetable"
const val PATH_ROUTE_MODULE_COURSE_SCHEDULE="/main/modules/course/courseSchedule"
const val PATH_ROUTE_MODULE_TIMETABLE="/main/modules/course/timetable"
const val PATH_ROUTE_MODULE="/main/modules"
const val PATH_ROUTE_LOGIN_FRAGMENT="/login/fragment"
const val NAME_INTERCEPTOR_LOGIN="LOGIN_INTERCEPTOR"
val routerModule= Kodein.Module(ROUTER_MODULE_NAME){
    bind<ARouter>() with singleton { ARouter.getInstance() }
}