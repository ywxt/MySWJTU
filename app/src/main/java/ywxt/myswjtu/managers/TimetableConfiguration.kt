package ywxt.myswjtu.managers

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ywxt.myswjtu.models.TimetableConfigurationModel

const val TIMETABLE_CONFIGURATION_FILE = "timetable_confiruation.json"

class TimetableConfiguration(
    private val storageManager: StorageManager
) {
    val showAllCourse: MutableLiveData<Boolean> = MutableLiveData()
    val notify: MutableLiveData<Boolean> = MutableLiveData()
    val notifyTime: MutableLiveData<Int> = MutableLiveData()

    init {
        getTimetableConfiguration()
    }

    fun getTimetableConfiguration() {
        storageManager.getString(TIMETABLE_CONFIGURATION_FILE)
            .observeOn(Schedulers.computation())
            .map {
                Gson().fromJson(it, TimetableConfigurationModel::class.java)
            }.observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                showAllCourse.value = it.showAllCourse
                notify.value = it.notify
                notifyTime.value = it.notifyTime
            }, {
                showAllCourse.value = true
                notify.value = false
                notifyTime.value = 0
            })
    }

    fun setTimetableConfiguration() {
        val model = TimetableConfigurationModel(
            showAllCourse = showAllCourse.value?:true,
            notify = notify.value?:false,
            notifyTime = notifyTime.value?:0
        )
        storageManager.writeString(
            TIMETABLE_CONFIGURATION_FILE, Gson().toJson(
                model
            )
        ).subscribe({
            
        },{
            
        })
    }
}