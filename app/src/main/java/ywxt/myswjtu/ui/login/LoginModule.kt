package ywxt.myswjtu.ui.login

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

const val LOGIN_MODULE_NAME = "LOGIN_MODULE_NAME"
val loginModule = Kodein.Module(LOGIN_MODULE_NAME){
    bind<LoginViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        ViewModelProviders.of(context.activity!!).get(LoginViewModel::class.java)
    }
    
}