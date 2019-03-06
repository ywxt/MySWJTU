package ywxt.myswjtu.ui.main.main

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import org.kodein.di.Kodein
import org.kodein.di.android.support.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

const val  MAIN_MODULE_NAME="MAIN_MODULE_NAME"
val mainModule= Kodein.Module(MAIN_MODULE_NAME){
    bind<MainViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton { 
        ViewModelProviders.of(context.activity!!).get(MainViewModel::class.java)
    }
}