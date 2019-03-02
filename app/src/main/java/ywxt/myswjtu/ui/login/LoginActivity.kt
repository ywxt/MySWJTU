package ywxt.myswjtu.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ywxt.myswjtu.R

/**
 * A login screen that offers login via email/password.
 */
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
