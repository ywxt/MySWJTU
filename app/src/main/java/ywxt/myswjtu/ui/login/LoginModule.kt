package ywxt.myswjtu.ui.login

import androidx.lifecycle.ViewModelProviders
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton
import ywxt.myswjtu.common.ui.BaseActivity
import ywxt.myswjtu.common.ui.BaseFragment
import ywxt.myswjtu.common.ui.ViewStateManager
import ywxt.myswjtu.common.viewmodels.FragmentViewModelFactory

const val LOGIN_MODULE_NAME = "LOGIN_MODULE_NAME"
val loginModule = Kodein.Module(LOGIN_MODULE_NAME) {

    bind<ViewStateManager>() with scoped<BaseFragment>(AndroidLifecycleScope).singleton {
        ViewStateManager(context.requireActivity() as BaseActivity)
    }

    bind<LoginViewModel>() with scoped<BaseFragment>(AndroidLifecycleScope).singleton {
        ViewModelProviders.of(context.requireActivity(), FragmentViewModelFactory(context))
            .get(LoginViewModel::class.java)
    }
}