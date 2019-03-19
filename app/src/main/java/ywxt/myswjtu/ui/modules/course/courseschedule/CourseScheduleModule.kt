package ywxt.myswjtu.ui.modules.course.courseschedule

import androidx.lifecycle.ViewModelProviders
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton
import ywxt.myswjtu.adapters.CourseScheduleAdapter
import ywxt.myswjtu.common.ui.BaseFragment
import ywxt.myswjtu.common.viewmodels.FragmentViewModelFactory
import ywxt.myswjtu.models.CourseModel

const val COURSE_SCHEDULE_MODULE_NAME = "COURSE_SCHEDULE_MODULE_NAME"
val courseScheduleModule = Kodein.Module(COURSE_SCHEDULE_MODULE_NAME) {
    bind<CourseScheduleViewModel>() with scoped<BaseFragment>(AndroidLifecycleScope).singleton {
        ViewModelProviders.of(context.requireActivity(), FragmentViewModelFactory(context))
            .get(CourseScheduleViewModel::class.java)
    }
    bind<MutableList<CourseModel>>() with scoped<BaseFragment>(AndroidLifecycleScope).singleton{
        mutableListOf<CourseModel>()
    }
    bind<CourseScheduleAdapter>() with scoped<BaseFragment>(AndroidLifecycleScope).singleton{
        CourseScheduleAdapter(instance())
    }
}