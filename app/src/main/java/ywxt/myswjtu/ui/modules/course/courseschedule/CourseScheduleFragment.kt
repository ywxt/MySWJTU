package ywxt.myswjtu.ui.modules.course.courseschedule

import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import ywxt.myswjtu.R
import ywxt.myswjtu.adapters.CourseScheduleAdapter
import ywxt.myswjtu.common.ui.DataBindingFragment
import ywxt.myswjtu.models.CourseModel
import ywxt.myswjtu.modules.PATH_ROUTE_MODULE_COURSE_SCHEDULE

@Route(path = PATH_ROUTE_MODULE_COURSE_SCHEDULE)
class CourseScheduleFragment :
    DataBindingFragment<ywxt.myswjtu.databinding.FragmentCourseScheduleBinding, CourseScheduleViewModel>() {
    override val viewModel: CourseScheduleViewModel by instance()
    override val layoutId: Int = R.layout.fragment_course_schedule

    private val adapter: CourseScheduleAdapter by instance()

    private val models: MutableList<CourseModel> by instance()

    override fun bindViewModel(dataBinding: ywxt.myswjtu.databinding.FragmentCourseScheduleBinding?) {
        if (dataBinding == null) return
        Log.i("Course","databinding")
        dataBinding.vm = viewModel
        dataBinding.recycleCourseSchedule.adapter = adapter
        dataBinding.recycleCourseSchedule.layoutManager=LinearLayoutManager(this.context)
        viewModel.courseScheduleModel.observe(this, Observer { list ->
            Log.i("Course","courseModelChanged")
            if (!models.isNullOrEmpty()) return@Observer
            models.addAll(list.courses)
            adapter.notifyDataSetChanged()

        })
        
    }

    override val kodein: Kodein = Kodein.lazy {
        extend(parentKodein)
        import(courseScheduleModule)
    }

}