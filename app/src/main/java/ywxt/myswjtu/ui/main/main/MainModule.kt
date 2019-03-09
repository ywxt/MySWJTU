package ywxt.myswjtu.ui.main.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.*

const val  MAIN_MODULE_NAME="MAIN_MODULE_NAME"
val mainModule= Kodein.Module(MAIN_MODULE_NAME){
    bind<MainViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton { 
        ViewModelProviders.of(context.activity!!).get(MainViewModel::class.java)
    }
    bind<MutableList<MainModuleViewModel>>() with   scoped<Fragment>(AndroidLifecycleScope).singleton { 
        mutableListOf<MainModuleViewModel>()
    }
}