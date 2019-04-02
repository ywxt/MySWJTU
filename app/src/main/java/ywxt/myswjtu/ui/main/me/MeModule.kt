package ywxt.myswjtu.ui.main.me

import androidx.lifecycle.ViewModelProviders
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton
import ywxt.myswjtu.common.ui.BaseFragment
import ywxt.myswjtu.common.viewmodels.FragmentViewModelFactory

const val ME_MODULE_NAME = "ME_MODULE_NAME"
val meModule = Kodein.Module(ME_MODULE_NAME) {
    bind<MeViewModel>() with scoped<BaseFragment>(AndroidLifecycleScope).singleton {
        ViewModelProviders.of(context.requireActivity(), FragmentViewModelFactory(context)).get(MeViewModel::class.java)
    }
    bind<MeDataSource>() with singleton { 
        MeDataSource(instance(),instance())
    }
}