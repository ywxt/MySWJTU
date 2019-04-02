package ywxt.myswjtu.ui.main.timetable

import androidx.lifecycle.ViewModelProviders
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton
import ywxt.myswjtu.common.ui.BaseFragment
import ywxt.myswjtu.common.viewmodels.FragmentViewModelFactory

const val TIMETABLE_MODULE_NAME = "TIMETABLE_MODULE_NAME"

val timetableModule = Kodein.Module(TIMETABLE_MODULE_NAME) {
    bind<TimetableViewModel>() with scoped<BaseFragment>(AndroidLifecycleScope).singleton {
        ViewModelProviders.of(context.requireActivity(), FragmentViewModelFactory(context))
            .get(TimetableViewModel::class.java)
    }
    bind<TimetableDataSource>() with singleton { 
        TimetableDataSource(instance(),instance())
    }
}