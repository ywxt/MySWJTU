package ywxt.myswjtu.managers

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import ywxt.myswjtu.http.CourseService
import ywxt.myswjtu.models.TimetableModel
import ywxt.myswjtu.utils.TimetableConverter

class TimetableManager(
    private val courseService: CourseService
) {

    fun getCourseSchedule(): Flowable<List<TimetableModel>> {
        return courseService.getCourseSchedule()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .map { scheduleModel ->
                val timetables= mutableListOf<TimetableModel>()
                scheduleModel.courses.forEach { 
                    timetables.addAll(TimetableConverter.converter(it))
                }
                timetables
            }
            

    }

}