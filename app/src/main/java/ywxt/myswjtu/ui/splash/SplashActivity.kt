package ywxt.myswjtu.ui.splash

import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import ywxt.myswjtu.R
import ywxt.myswjtu.common.ui.DataBindingActivity
import ywxt.myswjtu.databinding.ActivitySplashBinding

class SplashActivity : DataBindingActivity<ywxt.myswjtu.databinding.ActivitySplashBinding, SplashViewModel>() {
    override val viewModel: SplashViewModel by instance()
    override val layoutId: Int= R.layout.activity_splash
    override val kodein: Kodein = Kodein.lazy {
        extend(parentKodein)
        import(splashModule)
    }
    override fun bindViewModel(dataBinding: ActivitySplashBinding) {
        dataBinding.vm=viewModel
        
    }
}
