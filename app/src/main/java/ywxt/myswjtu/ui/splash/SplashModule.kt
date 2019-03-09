package ywxt.myswjtu.ui.splash

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

const val SPLASH_MODULE_NAME="SPLASH_MODULE_NAME"
val splashModule= Kodein.Module(SPLASH_MODULE_NAME){
    bind<SplashViewModel>() with scoped<FragmentActivity>(AndroidLifecycleScope).singleton { 
        ViewModelProviders.of(context).get(SplashViewModel::class.java)
    }
}