package ywxt.myswjtu.http

import io.reactivex.Flowable
import retrofit2.http.GET
import ywxt.myswjtu.models.CourseScheduleModel

interface CourseService {
    @GET("CourseAction?setAction=userCourseSchedule&selectTableType=ThisTerm")
    fun getCourseSchedule(): Flowable<CourseScheduleModel>
}