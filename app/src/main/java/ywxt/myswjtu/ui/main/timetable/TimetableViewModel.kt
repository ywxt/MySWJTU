package ywxt.myswjtu.ui.main.timetable

import androidx.lifecycle.MutableLiveData
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import ywxt.myswjtu.common.ui.BaseFragment
import ywxt.myswjtu.common.viewmodels.BaseFragmentViewModel
import ywxt.myswjtu.managers.ToastManager
import ywxt.myswjtu.models.TimetableModel
import java.io.FileNotFoundException

class TimetableViewModel(fragment: BaseFragment) : BaseFragmentViewModel(fragment) {
    private val dataSource: TimetableDataSource by instance()
    private val toastManager: ToastManager by instance()
    //private val alarmManager by lazy { fragment.context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager }
    val week: MutableLiveData<Int> = MutableLiveData()
    val timetable: MutableLiveData<MutableList<TimetableModel>> = MutableLiveData()

    val currentWeek: MutableLiveData<Int> = MutableLiveData()

    init {

        getDefaultTimetable()
        getWeek()
        getCustomizedTimetable()
    }


    fun getCustomizedTimetable() {
        dataSource.getCustomizedTimetable()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                timetable.value?.removeAll {
                    it.customized
                }
                val time = list.toMutableList().apply { addAll(timetable.value ?: listOf()) }
                timetable.value = time

            }, {
                when (it) {
                    is FileNotFoundException -> {
                    }
                    else -> {
                        toastManager.toast("加载自定义课程失败")
                    }
                }
            })
    }

    fun writeCustomizedTimetable() {
        dataSource.writeCustomizedTimetable(timetable.value ?: listOf())
    }

    fun getDefaultTimetable() {
        Flowable.concatArrayDelayError(
            dataSource.getLocalTimetable(),
            dataSource.getRemoteTimetable()
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->

                if (list.isNullOrEmpty()) return@subscribe
                timetable.value?.removeAll {
                    !it.customized
                }
                val time = list.toMutableList().apply { addAll(timetable.value ?: listOf()) }
                timetable.value = time
            }, {
                when (it) {
                    is FileNotFoundException -> {
                    }
                    else -> toastManager.toast(it.localizedMessage)
                }
            })
    }

    fun getWeek() {
        Flowable.concatArrayDelayError(
            dataSource.getLocalWeek(),
            dataSource.getRemoteWeek()
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (week.value == null) week.value = if (it == 0) 1 else it
                currentWeek.value = it
            }, {

                toastManager.toast(it.localizedMessage)

            })
    }

    override fun onCleared() {
        super.onCleared()
        writeCustomizedTimetable()
    }

//    fun NofityTimetable() {
//        
//        val time = timetable.value!!.filterNot { it.step == 0 || it.day == 0 }
//        for (i)
//            
//    }
}