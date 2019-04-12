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
    val color: Int,
    /**
     * 自定义
     */
    val customized: Boolean

) : ScheduleEnable {

    constructor() : this("", "", "", 0, 0, listOf(), 0, 0, false)
    

    override fun getSchedule(): Schedule = Schedule().apply {
        name = this@TimetableModel.name
        teacher = this@TimetableModel.teacher
        room = this@TimetableModel.room
        start = this@TimetableModel.start
        step = this@TimetableModel.step
        weekList = this@TimetableModel.weekList
        day = this@TimetableModel.day
        colorRandom = 0//this@TimetableModel.color
        putExtras("customized", customized)
    }
}