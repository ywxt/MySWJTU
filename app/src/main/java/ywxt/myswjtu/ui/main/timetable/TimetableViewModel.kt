package ywxt.myswjtu.ui.main.timetable

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import ywxt.myswjtu.common.ui.BaseFragment
import ywxt.myswjtu.common.viewmodels.BaseFragmentViewModel
import ywxt.myswjtu.managers.TimetableManager
import ywxt.myswjtu.managers.ToastManager
import ywxt.myswjtu.models.TimetableModel

class TimetableViewModel(fragment: BaseFragment):BaseFragmentViewModel(fragment){
    private val timetableManager : TimetableManager by instance()
    private val toastManager:ToastManager by instance()
    val timetable:MutableLiveData<List<TimetableModel>> = MutableLiveData()
    
    init {
        getTimetable()
    }
    
    fun getTimetable(){
        timetableManager.getCourseSchedule()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                timetable.value=it
            },{
                toastManager.toast(it.localizedMessage)
            })
    }
}