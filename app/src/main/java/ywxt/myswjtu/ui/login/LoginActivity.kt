package ywxt.myswjtu.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import ywxt.myswjtu.R
import ywxt.myswjtu.common.ui.BaseActivity
import ywxt.myswjtu.modules.PATH_ROUTE_LOGIN
import ywxt.myswjtu.modules.PATH_ROUTE_LOGIN_FRAGMENT

@Route(path = PATH_ROUTE_LOGIN)
class LoginActivity : BaseActivity() {
    override val kodein: Kodein = Kodein.lazy { 
        extend(parentKodein)
    }
    private val router by kodein.instance<ARouter>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Set up the login form.
        initFragment()
        //Log.e("Application",(this.application==null).toString())
    }

    private fun initFragment() {
        supportFragmentManager.apply {
            findFragmentByTag(TAG) ?: beginTransaction()
                .add(R.id.login_container, router.build(PATH_ROUTE_LOGIN_FRAGMENT).navigation() as Fragment, TAG)
                .commitAllowingStateLoss()
        }
    }

    
    companion object {
        const val TAG = "Login"
    }

}
