package ywxt.myswjtu.models

import com.zhuangfei.timetable.model.Schedule
import com.zhuangfei.timetable.model.ScheduleEnable

data class TimetableModel(
    /**
     * 课程名称
     */
    val name: String,
    /**
     * 老师
     */
    val teacher: String,
    /**
     * 教室
     */
    val room: String,
    /**
     * 开始节
     */
    val start: Int,
    /**
     * 几节
     */
    val step: Int,
    /**
     * 上的周次
     */
    val weekList: List<Int>,
    /**
     * 星期几
     */
    val day: Int,
    /**
     * 课程颜色
     */
    val color: Int

) : ScheduleEnable {

    override fun getSchedule(): Schedule = Schedule().apply {
        name = this@TimetableModel.name
        teacher = this@TimetableModel.teacher
        room = this@TimetableModel.room
        start = this@TimetableModel.start
        step = this@TimetableModel.step
        weekList = this@TimetableModel.weekList
        day = this@TimetableModel.day
        colorRandom = this@TimetableModel.color
    }
}