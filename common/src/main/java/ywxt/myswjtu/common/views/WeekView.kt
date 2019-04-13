package ywxt.myswjtu.common.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.zhuangfei.timetable.listener.IWeekView
import com.zhuangfei.timetable.listener.OnWeekItemClickedAdapter
import com.zhuangfei.timetable.listener.OnWeekLeftClickedAdapter
import com.zhuangfei.timetable.model.Schedule
import com.zhuangfei.timetable.model.ScheduleEnable
import com.zhuangfei.timetable.model.ScheduleSupport
import com.zhuangfei.timetable.model.WeekViewEnable
import com.zhuangfei.timetable.view.PerWeekView
import ywxt.myswjtu.common.R

class WeekView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : LinearLayout(context, attrs),
    WeekViewEnable<WeekView> {
    internal var mInflate: LayoutInflater

    //周次的容器
    internal lateinit var container: LinearLayout

    //跟布局
    internal lateinit var root: LinearLayout

    //左侧按钮
    internal lateinit var leftlayout: LinearLayout

    //数据
    private var dataSource: List<Schedule>? = null

    //布局保存
    private var layouts: MutableList<LinearLayout>? = null
    private var textViews: MutableList<TextView>? = null

    //当前周
    private var curWeek = 1
    private var preIndex = 1

    //多少项
    private var itemCount = 20

    private var onWeekItemClickedListener: IWeekView.OnWeekItemClickedListener? = null
    private var onWeekLeftClickedListener: IWeekView.OnWeekLeftClickedListener? = null

    /**
     * 获取Item点击监听
     * @return
     */
    fun onWeekItemClickedListener(): IWeekView.OnWeekItemClickedListener {
        if (onWeekItemClickedListener == null) onWeekItemClickedListener = OnWeekItemClickedAdapter()
        return onWeekItemClickedListener!!
    }

    /**
     * 设置Item点击监听
     * @param onWeekItemClickedListener
     * @return
     */
    fun callback(onWeekItemClickedListener: IWeekView.OnWeekItemClickedListener): WeekView {
        this.onWeekItemClickedListener = onWeekItemClickedListener
        return this
    }

    /**
     * 获取左侧按钮点击监听
     * @return
     */
    fun onWeekLeftClickedListener(): IWeekView.OnWeekLeftClickedListener {
        if (onWeekLeftClickedListener == null) onWeekLeftClickedListener = OnWeekLeftClickedAdapter()
        
        return onWeekLeftClickedListener!!
    }

    /**
     * 设置左侧按钮点击监听
     * @param onWeekLeftClickedListener
     * @return
     */
    fun callback(onWeekLeftClickedListener: IWeekView.OnWeekLeftClickedListener): WeekView {
        this.onWeekLeftClickedListener = onWeekLeftClickedListener
        return this
    }

    /**
     * 设置当前周
     * @param curWeek
     * @return
     */
    override fun curWeek(curWeek: Int): WeekView {
        var curWeek = curWeek
        if (curWeek < 1) curWeek = 1
        this.curWeek = curWeek
        return this
    }

    /**
     * 设置项数
     * @param count
     * @return
     */
    override fun itemCount(count: Int): WeekView {
        if (count <= 0) return this
        this.itemCount = count
        return this
    }

    override fun itemCount(): Int {
        return itemCount
    }

    /**
     * 设置数据源
     * @param list
     * @return
     */
    override fun source(list: List<ScheduleEnable>): WeekView {
        data(ScheduleSupport.transform(list))
        return this
    }

    /**
     * 设置数据源
     * @param scheduleList
     * @return
     */
    override fun data(scheduleList: List<Schedule>?): WeekView? {
        if (scheduleList == null) return null
        this.dataSource = scheduleList
        return this
    }

    /**
     * 获取数据源
     * @return
     */
    override fun dataSource(): List<Schedule> {
        if (dataSource == null) dataSource = ArrayList()
        return dataSource!!
    }

    init {
        mInflate = LayoutInflater.from(context)
        initView()
    }

    private fun initView() {
        mInflate.inflate(R.layout.ywxt_view_weekview, this)
        container = findViewById(R.id.weekview_container)
        root = findViewById(R.id.id_root)
        leftlayout = findViewById(R.id.weekview_leftlayout)
    }

    /**
     * 初次构建时调用，显示周次选择布局
     */
    override fun showView(): WeekView {
        if (curWeek < 1) curWeek(1)
        if (curWeek > itemCount()) curWeek = itemCount

        container.removeAllViews()
        layouts = ArrayList()
        textViews = ArrayList()

        leftlayout.setOnClickListener { onWeekLeftClickedListener().onWeekLeftClicked() }

        for (i in 1..itemCount) {
            val view = mInflate.inflate(R.layout.ywxt_item_weekview, null)
            val perLayout =
                view.findViewById<LinearLayout>(R.id.perweekview_layout)
            val weekText = view.findViewById<TextView>(R.id.weektext)
            val bottomText =
                view.findViewById<TextView>(R.id.weektext_bottom)

            weekText.text = "第${i}周"
            if (i == curWeek) bottomText.text = "(本周)"
            val perWeekView =
                view.findViewById<PerWeekView>(R.id.perweekview)
            perWeekView.setData(dataSource(), i)
            perLayout.setOnClickListener {
                resetBackground()
                preIndex = i
                perLayout.background = ContextCompat.getDrawable(context,R.drawable.ywxt_weekview_white)
                onWeekItemClickedListener().onWeekClicked(i)
            }

            layouts!!.add(perLayout)
            textViews!!.add(bottomText)
            container.addView(view)
        }
        if (curWeek > 0 && curWeek <= layouts!!.size) {
            layouts!![curWeek - 1].background = ContextCompat.getDrawable(context,R.drawable.ywxt_weekview_thisweek)
        }
        return this
    }

    /**
     * 当前周被改变后可以调用该方式修正一下底部的文本
     * @return
     */
    override fun updateView(): WeekView {
        if (layouts == null || layouts!!.size == 0) return this
        if (textViews == null || textViews!!.size == 0) return this

        for (i in layouts!!.indices) {
            if (curWeek - 1 == i) {
                textViews!![i].text = "(本周)"
            } else {
                textViews!![i].text = ""
            }
            layouts!![i].setBackgroundColor(ContextCompat.getColor(context,R.color.colorWeekBg))
        }

        if (curWeek > 0 && curWeek <= layouts!!.size) {
            layouts!![curWeek - 1].background = ContextCompat.getDrawable(context,R.drawable.ywxt_weekview_thisweek)
        }
        return this
    }

    /**
     * 重置背景色
     */
    fun resetBackground() {
        layouts!![preIndex - 1].setBackgroundColor(ContextCompat.getColor(context,R.color.colorWeekBg))
        layouts!![curWeek - 1].background = ContextCompat.getDrawable(context,R.drawable.ywxt_weekview_thisweek)
    }

    /**
     * 隐藏左侧设置当前周的控件
     */
    fun hideLeftLayout(): WeekView {
        leftlayout.visibility = View.GONE
        return this
    }

    /**
     * 设置控件的可见性
     * @param isShow true:显示，false:隐藏
     */
    override fun isShow(isShow: Boolean): WeekView {
        if (isShow) {
            root.visibility = View.VISIBLE
        } else {
            root.visibility = View.GONE
        }
        return this
    }

    /**
     * 判断该控件是否显示
     * @return
     */
    override fun isShowing(): Boolean {
        return root.visibility != View.GONE
    }

    companion object {

        private val TAG = "WeekView"
    }
}