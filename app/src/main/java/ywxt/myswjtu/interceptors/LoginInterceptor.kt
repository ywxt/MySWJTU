package ywxt.myswjtu.interceptors

import android.content.Context
import android.content.Intent
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import com.alibaba.android.arouter.launcher.ARouter
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import ywxt.myswjtu.App
import ywxt.myswjtu.common.exceptions.NotSignedException
import ywxt.myswjtu.managers.UserManager
import ywxt.myswjtu.modules.NAME_INTERCEPTOR_LOGIN
import ywxt.myswjtu.modules.PATH_ROUTE_LOGIN

@Interceptor(priority = 10, name = NAME_INTERCEPTOR_LOGIN)
class LoginInterceptor : IInterceptor {
    private lateinit var context: Context
    private val kodein by kodein(App.INSTANCE)
    private val userManager by kodein.instance<UserManager>()
    private val router by kodein.instance<ARouter>()
    override fun process(postcard: Postcard?, callback: InterceptorCallback?) {
        if (postcard?.path == PATH_ROUTE_LOGIN) {
            callback?.onContinue(postcard) 
            return
        }
        postcard.let {
            
            if (!userManager.signed) {
                callback?.onInterrupt(NotSignedException("未登录"))
                router.build(PATH_ROUTE_LOGIN).withFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK).navigation(context)
                return
            }
            callback?.onContinue(it)
        }
    }

    override fun init(context: Context?) {
        if (context == null) return
        this.context = context
    }
}