package ywxt.myswjtu.ui.main.timetable

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.zhuangfei.timetable.TimetableView
import com.zhuangfei.timetable.listener.ISchedule
import com.zhuangfei.timetable.listener.IWeekView
import com.zhuangfei.timetable.listener.OnItemBuildAdapter
import com.zhuangfei.timetable.model.Schedule
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import ywxt.myswjtu.R
import ywxt.myswjtu.common.ui.DataBindingFragment
import ywxt.myswjtu.databinding.FragmentTimetableBinding
import ywxt.myswjtu.managers.TimetableConfiguration
import ywxt.myswjtu.managers.ToastManager
import ywxt.myswjtu.models.TimetableModel
import ywxt.myswjtu.modules.PATH_ROUTE_MAIN_TIMETABLE

@Route(path = PATH_ROUTE_MAIN_TIMETABLE)
class TimetableFragment : DataBindingFragment<FragmentTimetableBinding, TimetableViewModel>() {
    override val viewModel: TimetableViewModel by instance()
    override val layoutId: Int = R.layout.fragment_timetable
    private val configuration: TimetableConfiguration by instance()
    val dataSource: TimetableDataSource by instance()
    private val toastManager: ToastManager by instance()
    override fun bindViewModel(dataBinding: FragmentTimetableBinding?) {
        if (dataBinding == null) return
        dataBinding.vm = viewModel
        dataBinding.idWeekview.hideLeftLayout()
            .itemCount(23)
            .callback(IWeekView.OnWeekItemClickedListener { week ->
                dataBinding.idTimetableView.onDateBuildListener()
                    .onUpdateDate(viewModel.currentWeek.value ?: 0, week)
                dataBinding.idTimetableView.changeWeekOnly(week)
                viewModel.week.value = week

            })
        
        dataBinding.idTimetableView
            .maxSlideItem(13)
            .callback(ISchedule.OnFlaglayoutClickListener { day, start ->
                dataBinding.idTimetableView.hideFlaglayout()
                dataBinding.idTimetableView.showAppendCourseDialog(
                    viewModel.week.value ?: dataBinding.idTimetableView.curWeek(),
                    day,
                    start
                )
            })
            .callback(object :ISchedule.OnItemBuildListener{
                val adapter=OnItemBuildAdapter()
                override fun getItemText(schedule: Schedule?, isThisWeek: Boolean): String {
                    return adapter.getItemText(schedule,isThisWeek)
                }

                override fun onItemUpdate(
                    layout: FrameLayout?,
                    textView: TextView?,
                    countTextView: TextView?,
                    schedule: Schedule?,
                    gd: GradientDrawable?
                ) {
                    if (schedule?.extras?.get("customzied") as Boolean || dataBinding.idTimetableView.curWeek()<= schedule.weekList.first()){
                        layout?.visibility=View.GONE
                    }else{
                        adapter.onItemUpdate(layout,textView,countTextView,schedule,gd)
                    }
                }

            })
        viewModel.timetable.observe(this, Observer { list ->
            val timetable = list.filterNot { it.day == 0 || it.step == 0 }
            dataBinding.idWeekview
                .source(timetable)
                .showView()
            dataBinding.idTimetableView.onDateBuildListener()
                .onUpdateDate(viewModel.currentWeek.value ?: 1, viewModel.week.value ?: 1)
            dataBinding.idTimetableView
                .source(timetable)
                .updateView()
            dataBinding.idTimetableView.changeWeekOnly(viewModel.week.value ?: 1)
        })
        viewModel.currentWeek.observe(this, Observer {
            dataBinding.idTimetableView.curWeek(it).updateView()
            dataBinding.idWeekview.curWeek(it).updateView()
        })
        configuration.showAllCourse.observe(this, Observer {
            dataBinding.idTimetableView.isShowNotCurWeek(it)
                .updateView()
        })
        configuration.showWeekend.observe(this, Observer {
            dataBinding.idTimetableView.isShowWeekends(it)
                .updateView()
        })
    }

    override val kodein: Kodein = Kodein.lazy {
        extend(parentKodein)
        import(timetableModule)
    }

    inner class TimetableViewModelFactory : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(TimetableDataSource::class.java).newInstance(dataSource)
        }

    }


    private fun TimetableView.showAppendCourseDialog(week: Int, day: Int, start: Int) {
        val stepTextView = EditText(this@TimetableFragment.context).apply {
            inputType = EditorInfo.TYPE_NUMBER_FLAG_DECIMAL
        }
        val dialog = AlertDialog.Builder(this@TimetableFragment.context!!).setTitle("输入事件")
            .setView(stepTextView)
            .setPositiveButton("确认") { d: DialogInterface, _: Int ->


                if (stepTextView.text.isNullOrEmpty()) {
                    toastManager.toast("事件不为空不正确")
                } else {
                    viewModel.timetable.value?.add(
                        TimetableModel(
                            name = stepTextView.text.toString(),
                            customized = true,
                            color = 0,
                            teacher = "",
                            start = start,
                            weekList = listOf(week),
                            day = day + 1,
                            room = "自定义",
                            step = 1
                        )
                    )
                    viewModel.timetable.value = viewModel.timetable.value
                }
            }.setNegativeButton("取消") { d, _ -> d.cancel() }
            .setCancelable(true)
            .show()
    }


}
