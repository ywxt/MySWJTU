package ywxt.myswjtu.common.views

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import ywxt.myswjtu.common.R


class WeekView : com.zhuangfei.timetable.view.WeekView {
    private val root =
        com.zhuangfei.timetable.view.WeekView::class.java.getDeclaredField("root").get(this) as LinearLayout
    private val container =
        com.zhuangfei.timetable.view.WeekView::class.java.getDeclaredField("container").get(this) as LinearLayout

    constructor(context: Context) : super(context) {
        root.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
        container.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
    }

    constructor(context: Context, arrts: AttributeSet) : super(context, arrts) {

        root.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
        container.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
    }


}