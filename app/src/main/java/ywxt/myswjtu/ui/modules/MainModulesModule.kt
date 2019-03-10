package ywxt.myswjtu.ui.modules

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

const val MAIN_MODULES_MODULE_NAME = "MIAN_MODULES_MODULE_NAME"
val mainModulesModule = Kodein.Module(MAIN_MODULES_MODULE_NAME) {
    bind<MutableList<Fragment>>() with scoped<FragmentActivity>(AndroidLifecycleScope).singleton {
        mutableListOf<Fragment>()
    }
    bind<ModuleViewModel>() with scoped<FragmentActivity>(AndroidLifecycleScope).singleton {
        ViewModelProviders.of(context).get(ModuleViewModel::class.java)
    }
}