package ywxt.myswjtu.modules

import com.alibaba.android.arouter.launcher.ARouter
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

const val ROUTER_MODULE_NAME="ROUTER_MODULE_NAME"
const val PATH_ROUTE_LOGIN="/login/login"
const val PATH_ROUTE_MAIN="/main/main"
const val NAME_ROUTE_LOGIN="login"
const val NAME_ROUTE_MAIN="main"
const val NAME_INTERCEPTOR_LOGIN="LOGIN_INTERCEPTOR"
val routerModule= Kodein.Module(ROUTER_MODULE_NAME){
    bind<ARouter>() with singleton { ARouter.getInstance() }
}