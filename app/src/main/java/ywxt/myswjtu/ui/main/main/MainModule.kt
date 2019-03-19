package ywxt.myswjtu.ui.main.main

import androidx.lifecycle.ViewModelProviders
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton
import ywxt.myswjtu.adapters.MainModuleAdapter
import ywxt.myswjtu.common.ui.BaseFragment
import ywxt.myswjtu.common.viewmodels.FragmentViewModelFactory

const val  MAIN_MODULE_NAME="MAIN_MODULE_NAME"
val mainModule= Kodein.Module(MAIN_MODULE_NAME){
    bind<MainViewModel>() with scoped<BaseFragment>(AndroidLifecycleScope).singleton { 
        ViewModelProviders.of(context.requireActivity(), FragmentViewModelFactory(context)).get(MainViewModel::class.java)
    }
    bind<MutableList<MainModuleViewModel>>() with scoped<BaseFragment>(AndroidLifecycleScope).singleton { 
        mutableListOf<MainModuleViewModel>()
    }
    bind<MainModuleAdapter>() with scoped<BaseFragment>(AndroidLifecycleScope).singleton {
        MainModuleAdapter(instance())
    }
}