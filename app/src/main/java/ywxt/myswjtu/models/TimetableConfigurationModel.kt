package ywxt.myswjtu.models

data class TimetableConfigurationModel(
    val showAllCourse:Boolean,
    val notify:Boolean,
    val notifyTime:Int,
    val showWeekend:Boolean
)