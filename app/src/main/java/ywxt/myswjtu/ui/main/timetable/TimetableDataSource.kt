package ywxt.myswjtu.ui.main.timetable

import io.reactivex.Flowable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import ywxt.myswjtu.managers.ConfigurationManager
import ywxt.myswjtu.managers.TimetableManager
import ywxt.myswjtu.models.TimetableModel

class TimetableDataSource(
    private val configurationManager: ConfigurationManager,
    private val timetableManager: TimetableManager
) {

    fun getLocalTimetable(): Flowable<List<TimetableModel>> =
        configurationManager.getTimetable().onErrorResumeNext(Function {
                Flowable.just(listOf())
            })

    fun getRemoteTimetable(): Flowable<List<TimetableModel>> =
        timetableManager.getCourseSchedule().observeOn(Schedulers.io()).doOnNext {
            configurationManager.writeTimetable(it)
        }

    fun getRemoteWeek(): Flowable<Int> =
        timetableManager.getCurrentWeek().doOnNext { configurationManager.writeWeek(it) }

    fun getLocalWeek(): Flowable<Int> = configurationManager.getWeek().onErrorResumeNext(Function { 
        Flowable.just(1)
    })

}