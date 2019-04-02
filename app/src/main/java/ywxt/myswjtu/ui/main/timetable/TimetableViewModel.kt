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
    val timetable: MutableLiveData<List<TimetableModel>> = MutableLiveData()
    val currentWeek: MutableLiveData<Int> = MutableLiveData()

    init {
       
        getTimetable()
        getWeek()
       
    }


    fun getTimetable(){
        Flowable.concatArrayDelayError(
            dataSource.getLocalTimetable(),
            dataSource.getRemoteTimetable()
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                if (it.isNullOrEmpty()) return@subscribe
                timetable.value = it
            }, {
                when (it) {
                    is FileNotFoundException -> {
                    }
                    else -> toastManager.toast(it.localizedMessage)
                }
            })
    }
    
    fun getWeek(){
        Flowable.concatArrayDelayError(
            dataSource.getLocalWeek(),
            dataSource.getRemoteWeek()
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                currentWeek.value = it
            }, {

                toastManager.toast(it.localizedMessage)

            })
    }
}