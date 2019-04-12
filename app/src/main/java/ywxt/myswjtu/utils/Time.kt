package ywxt.myswjtu.utils

import java.util.*

class Time(
    var hour: Int,
    var minute: Int,
    var second: Int
) {
    constructor(calendar: Calendar) : this(
        calendar.get(Calendar.HOUR),
        calendar.get(Calendar.MINUTE),
        calendar.get(Calendar.SECOND)
    )

    init {
        if (hour !in 0..23 || minute !in 0..59 || minute !in 0..60) {
            throw IllegalArgumentException("初始化时间错误")
        }
    }

    operator fun plusAssign(time: Time) {
        var s = 0
        var m = 0
        second += time.second
        minute += time.minute
        hour += time.hour
        if (second >= 60) {
            s = second / 60
            second -= s * 60

        }
        minute += s
        if (minute >= 60) {
            m = minute / 60
            minute -= m * 60
        }
        hour += m
        if (hour >= 24) {
            hour -= (hour / 24) * 24
        }
    }

    operator fun plus(time: Time): Time {
        var hour: Int = this.hour + time.hour
        var second: Int = this.second + time.second
        var minute: Int = this.minute + time.minute
        var s = 0
        var m = 0
        if (second >= 60) {
            s = second / 60
            second -= s * 60

        }
        minute += s
        if (minute >= 60) {
            m = minute / 60
            minute -= m * 60
        }
        hour += m
        if (hour >= 24) {
            hour -= (hour / 24) * 24
        }
        return Time(hour, minute, second)
    }

    operator fun minus(time: Time): Time {
        var hour: Int = this.hour - time.hour
        var second: Int = this.second - time.second
        var minute: Int = this.minute - time.minute
        var s = 1
        var m = 1
        if (second < 0) {
            s = second / 60
            second += (-s + 1) * 60

        }
        minute += s - 1
        if (minute < 0) {
            m = minute / 60
            minute += (-m + 1) * 60
        }
        hour += m - 1
        if (hour < 0) {
            hour += (-(hour / 24) + 1) * 24
        }
        return Time(hour, minute, second)
    }

    operator fun minusAssign(time: Time) {
        var s = 1
        var m = 1
        second -= time.second
        minute -= time.minute
        hour -= time.hour
        if (second < 0) {
            s = second / 60
            second += (-s + 1) * 60

        }
        minute += s - 1
        if (minute < 0) {
            m = minute / 60
            minute += (-m + 1) * 60
        }
        hour += m - 1
        if (hour < 0) {
            hour += (-(hour / 24) + 1) * 24
        }
    }

    fun toMillsecond(): Long {
        return (((hour.toLong() * 24) + minute.toLong()) * 60 + second.toLong()) * 1000
    }
}