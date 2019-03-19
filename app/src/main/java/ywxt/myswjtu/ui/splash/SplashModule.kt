package ywxt.myswjtu.ui.splash

import androidx.lifecycle.ViewModelProviders
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton
import ywxt.myswjtu.common.ui.BaseActivity
import ywxt.myswjtu.common.viewmodels.ActivityViewModelFactory

const val SPLASH_MODULE_NAME = "SPLASH_MODULE_NAME"
val splashModule = Kodein.Module(SPLASH_MODULE_NAME) {
    bind<SplashViewModel>() with scoped<BaseActivity>(AndroidLifecycleScope).singleton {
        ViewModelProviders.of(context,ActivityViewModelFactory(context)).get(SplashViewModel::class.java)
    }
}