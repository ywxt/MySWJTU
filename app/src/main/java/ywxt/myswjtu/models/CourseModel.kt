package ywxt.myswjtu.models

import me.ghui.fruit.annotations.Pick

data class CourseModel(

    /**
     * 名称
     */
    @Pick("td:nth-child(5)")
    val name: String = "",
    /**
     * 课程代码
     */
    @Pick("td:nth-child(4)")
    val code: Int = 0,
    /**
     * 任课老师
     */
    @Pick("td:nth-child(8)")
    val teacher: String = "",

    @Pick("td:nth-child(11)")
    val timeAndPlace: String = "",
    /**
     * 开课学院
     */
    @Pick("td:nth-child(7)")
    val school: String = ""
)