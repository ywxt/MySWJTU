package ywxt.myswjtu.ui.main.timetable

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.zhuangfei.timetable.TimetableView
import com.zhuangfei.timetable.listener.ISchedule
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
        viewModel.timetable.observe(this, Observer { list ->
            dataBinding.idWeekview.source(list.filterNot { it.day == 0 || it.step == 0 })
                .hideLeftLayout()
                .itemCount(23)
                .callback { week ->
                    dataBinding.idTimetableView.onDateBuildListener()
                        .onUpdateDate(viewModel.currentWeek.value ?: 0, week)
                    dataBinding.idTimetableView.changeWeekOnly(week)
                }
                .showView()
            dataBinding.idTimetableView
                .source(list.filterNot { it.day == 0 || it.step == 0 })
                .maxSlideItem(13)
                .callback(ISchedule.OnFlaglayoutClickListener { day, start ->
                    dataBinding.idTimetableView.showAppendCourseDialog(viewModel.currentWeek.value!!, day, start)
                })
                .showView()
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
                            day = day,
                            room = "",
                            step = 1
                        )
                    )
                    this.updateView()
                    d.dismiss()
                }
            }.setNegativeButton("取消") { d, _ -> d.cancel() }
            .setCancelable(true)
            .show()

    }


}
