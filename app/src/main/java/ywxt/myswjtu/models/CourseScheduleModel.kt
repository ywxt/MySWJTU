package ywxt.myswjtu.models

import me.ghui.fruit.annotations.Pick

@Pick("#table3 > tbody")
data class CourseScheduleModel(
    @Pick("tr")
    val courses: List<CourseModel> = listOf()
)