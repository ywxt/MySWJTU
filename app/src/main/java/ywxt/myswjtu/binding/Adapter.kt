package ywxt.myswjtu.binding

import androidx.databinding.BindingAdapter
import com.zhuangfei.timetable.TimetableView
import com.zhuangfei.timetable.view.WeekView
import ywxt.myswjtu.models.TimetableModel

@BindingAdapter("android:scheduleSource", "android:currentWeek")
fun  setTimetableViewSource(timetableView: TimetableView, source: List<TimetableModel>, currentWeek: Int?)  {
    if (currentWeek == null) return
    timetableView
        .source(source.filterNot { it.schedule.day == 0 || it.schedule.step == 0 })
        .curWeek(currentWeek)
        .showView()
}

@BindingAdapter("android:source", "android:currentWeek")
fun  setWeekViewSource(weekView: WeekView, source: List<TimetableModel>, currentWeek: Int?)  {
    if (currentWeek == null) return
    weekView
        .source(source.filterNot { it.schedule.day == 0 || it.schedule.step == 0 })
        .curWeek(currentWeek)
        .showView()
}