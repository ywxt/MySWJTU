package ywxt.myswjtu.ui.modules

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton
import ywxt.myswjtu.common.adapters.FragmentAdapter
import ywxt.myswjtu.common.ui.BaseActivity
import ywxt.myswjtu.common.ui.BaseFragment
import ywxt.myswjtu.common.viewmodels.ActivityViewModelFactory

const val MAIN_MODULES_MODULE_NAME = "MIAN_MODULES_MODULE_NAME"
val mainModulesModule = Kodein.Module(MAIN_MODULES_MODULE_NAME) {
    bind<MutableList<BaseFragment>>() with scoped<BaseActivity>(AndroidLifecycleScope).singleton {
        mutableListOf<BaseFragment>()
    }
    bind<ModuleViewModel>() with scoped<BaseActivity>(AndroidLifecycleScope).singleton {
        ViewModelProviders.of(context,ActivityViewModelFactory(context)).get(ModuleViewModel::class.java)
    }
    bind<FragmentAdapter>() with scoped<BaseActivity>(AndroidLifecycleScope).singleton {
        FragmentAdapter(
            instance(),
            context.supportFragmentManager
        )
    }
}