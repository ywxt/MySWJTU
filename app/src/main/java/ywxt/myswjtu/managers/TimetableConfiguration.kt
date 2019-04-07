package ywxt.myswjtu.managers

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import io.reactivex.schedulers.Schedulers
import ywxt.myswjtu.models.TimetableConfigurationModel

const val TIMETABLE_CONFIGURATION_FILE = "timetable_confiruation.json"

class TimetableConfiguration(
    private val storageManager: StorageManager
) {
    val showAllCourse: MutableLiveData<Boolean> = MutableLiveData()
    val notify: MutableLiveData<Boolean> = MutableLiveData()
    val notifyTime: MutableLiveData<Int> = MutableLiveData()
    val showWeekend: MutableLiveData<Boolean> = MutableLiveData()

    init {
        getTimetableConfiguration()
    }

    fun getTimetableConfiguration() {
        storageManager.getString(TIMETABLE_CONFIGURATION_FILE)
            .observeOn(Schedulers.computation())
            .map {
                Gson().fromJson(it, TimetableConfigurationModel::class.java)
            }
            .subscribe({
                showAllCourse.postValue(it.showAllCourse)
                notify.postValue(it.notify)
                notifyTime.postValue(it.notifyTime)
                showWeekend.postValue(it.showWeekend)
            }, {
                showAllCourse.postValue(true)
                notify.postValue(false)
                notifyTime.postValue(0)
                showWeekend.postValue(true)
            })
    }

    fun saveTimetableConfiguration() {
        val model = TimetableConfigurationModel(
            showAllCourse = showAllCourse.value ?: true,
            notify = notify.value ?: false,
            notifyTime = notifyTime.value ?: 0,
            showWeekend = showWeekend.value ?: true
        )
        storageManager.writeString(
            TIMETABLE_CONFIGURATION_FILE, Gson().toJson(
                model
            )
        ).subscribe({

        }, {

        })
    }
}