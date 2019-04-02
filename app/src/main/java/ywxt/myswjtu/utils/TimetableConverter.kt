package ywxt.myswjtu.utils

import ywxt.myswjtu.models.CourseModel
import ywxt.myswjtu.models.TimetableModel
import java.lang.Exception
import java.lang.StringBuilder

/**
 * 解析课表
 */
object TimetableConverter {

    fun converter(courseModel: CourseModel): List<TimetableModel> {
        val timetables = mutableListOf<TimetableModel>()
        val timeAndPlace = courseModel.timeAndPlace
        try {
            val tmpTime = timeAndPlace.split(' ')
            when {
                tmpTime.size == 2 -> {
                    val weeks = if (tmpTime.isNotEmpty()) getWeek(tmpTime[0]) else listOf()
                    val room = getRoom(tmpTime[1])
                    val color = getColor(courseModel.code)
                    timetables += TimetableModel(
                        weekList = weeks,
                        day = 0,
                        start = 0,
                        step = 0,
                        room = room,
                        color = color,
                        name = courseModel.name,
                        teacher = courseModel.teacher
                    )
                }
                tmpTime.size >= 4 -> {
                    for (t in 0 until tmpTime.size step 4) {
                        val weeks = getWeek(tmpTime[t])
                        val day = getDay(tmpTime[t + 1])
                        val (start, step) = getStartAndStep(tmpTime[t + 2])
                        val room = getRoom(tmpTime[t + 3])
                        val color = getColor(courseModel.code)
                        timetables += TimetableModel(
                            weekList = weeks,
                            day = day,
                            start = start,
                            step = step,
                            room = room,
                            color = color,
                            name = courseModel.name,
                            teacher = courseModel.teacher
                        )
                    }
                }
            }
        } catch (e: Exception) {
            return timetables.apply {
                add(
                    TimetableModel(
                        name = courseModel.name,
                        teacher = courseModel.teacher,
                        room = "地点未安排",
                        start = 0,
                        step = 0,
                        weekList = listOf(),
                        day = 0,
                        color = courseModel.code
                    )
                )
            }
        }
        return timetables

    }

    private fun getRoom(text: String): String = text
    private fun getWeek(text: String): List<Int> {
        val tmpText = StringBuilder(text)
        if (tmpText.endsWith('周')) tmpText.deleteCharAt(tmpText.lastIndex)
        try {
            val weeks: List<String>
            return if (tmpText.contains('-')) {
                weeks = tmpText.split('-')
                List(weeks[1].toInt() - weeks[0].toInt() + 1) {
                    weeks[0].toInt() + it
                }
            } else {
                weeks = tmpText.split(',')
                List(weeks.size) {
                    weeks[it].toInt()
                }
            }
        } catch (e: Exception) {
            return listOf()
        }
    }

    private fun getColor(code: Int) = code
    private fun getDay(text: String): Int {
        return chineseWeekToNumber(text.last())
    }

    private fun getStartAndStep(text: String): Pair<Int, Int> {
        val tmpText = StringBuilder(text)
        if (tmpText.endsWith('节')) tmpText.deleteCharAt(tmpText.lastIndex)
        return try {
            val weeks: List<String> = tmpText.split('-')
            Pair(weeks[0].toInt(), weeks[1].toInt() - weeks[0].toInt() + 1)
        } catch (e: Exception) {
            Pair(0, 0)
        }

    }

    /**
     * 中文星期转数字
     */
    private fun chineseWeekToNumber(text: Char): Int {
        return when (text) {
            '一' -> 1

            '二' -> 2

            '三' -> 3

            '四' -> 4

            '五' -> 5

            '六' -> 6

            '天' -> 7

            '日' -> 7

            else -> 0
        }
    }
}