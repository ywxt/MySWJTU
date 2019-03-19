package ywxt.myswjtu.ui.modules.course.courseschedule

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import ywxt.myswjtu.common.ui.BaseFragment
import ywxt.myswjtu.common.viewmodels.BaseFragmentViewModel
import ywxt.myswjtu.managers.ToastManager
import ywxt.myswjtu.managers.UserManager
import ywxt.myswjtu.models.CourseScheduleModel

class CourseScheduleViewModel(fragment: BaseFragment) : BaseFragmentViewModel(fragment) {

    private val userManager: UserManager by instance()

    private val toastManager: ToastManager by instance()

    val courseScheduleModel: MutableLiveData<CourseScheduleModel> = MutableLiveData()


    init {
        getCourseSchedule()
    }

    fun getCourseSchedule() {
        userManager.getCourseSchedule()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val courses = it.courses.filterIndexed { index, _ -> index != 0 }
                val scheduleModel = it.copy(courses = courses)
                courseScheduleModel.value = scheduleModel
            }, {
                toastManager.toast(it.message ?: "获取课程失败")
            })
    }
}