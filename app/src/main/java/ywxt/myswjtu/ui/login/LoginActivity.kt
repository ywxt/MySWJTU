package ywxt.myswjtu.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import ywxt.myswjtu.R
import ywxt.myswjtu.modules.NAME_ROUTE_LOGIN
import ywxt.myswjtu.modules.PATH_ROUTE_LOGIN

@Route(path = PATH_ROUTE_LOGIN,name = NAME_ROUTE_LOGIN)
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Set up the login form.
        initFragment()
    }

    private fun initFragment() {
        supportFragmentManager.apply {
            findFragmentByTag(TAG) ?: beginTransaction()
                .add(R.id.login_container, LoginFragment(), TAG)
                .commitAllowingStateLoss()
        }
    }

    
    companion object {
        const val TAG = "Login"
    }

}
