package ywxt.myswjtu.managers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ywxt.myswjtu.models.MainModuleModel
import ywxt.myswjtu.models.TimetableModel
import ywxt.myswjtu.models.UserModel

const val TIMETABLE_FILE_NAME = "timetable.json"
const val TIMETABLE_WEEK_NAME = "currentWeek"
const val USERMODEL_FILE_NAME = "usermodel.json"
const val COOKIE_FILE_NAME = "cookie.json"

class ConfigurationManager(
    private val storageManager: StorageManager
) {

    fun getMainModules(): Flowable<List<MainModuleModel>> {
        return storageManager.getString("main/mainModules.json")
            .observeOn(Schedulers.computation())
            .map {
                Gson().fromJson<List<MainModuleModel>>(it, object : TypeToken<List<MainModuleModel>>() {}.type)
            }

    }

    fun writeTimetable(list: List<TimetableModel>) {
        storageManager.writeString(TIMETABLE_FILE_NAME, Gson().toJson(list))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun getTimetable(): Flowable<List<TimetableModel>> {
        return storageManager.getString(TIMETABLE_FILE_NAME)
            .map { Gson().fromJson<List<TimetableModel>>(it, object : TypeToken<List<TimetableModel>>() {}.type) }

    }

    fun getWeek(): Flowable<Int> {
        return Flowable.create<Int>({
            val week = storageManager.getSharedPreferencesInt(TIMETABLE_WEEK_NAME)
            it.onNext(week)

        }, BackpressureStrategy.LATEST)
    }

    fun writeWeek(week: Int) {
        storageManager.writeSharedPreferences(TIMETABLE_WEEK_NAME) {
            putInt(TIMETABLE_WEEK_NAME, week)
        }
    }

    fun getUser(): Flowable<UserModel> {
        return storageManager.getString(USERMODEL_FILE_NAME)
            .observeOn(Schedulers.computation())
            .map { Gson().fromJson(it, UserModel::class.java) }

    }

    fun writeUser(user: UserModel) {
        storageManager.writeString(USERMODEL_FILE_NAME, Gson().toJson(user))
    }


    fun writeCookie(cookie: String) {
        storageManager.writeString(COOKIE_FILE_NAME, cookie).subscribe({},{})
    }
    
    fun getCookie():Flowable<String> = storageManager.getString(COOKIE_FILE_NAME)

}