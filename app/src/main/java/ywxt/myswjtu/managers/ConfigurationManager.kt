package ywxt.myswjtu.managers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import ywxt.myswjtu.models.MainModuleModel

class ConfigurationManager(
    private val storageManager: StorageManager
) {
    
    fun getMainModules(): Flowable<List<MainModuleModel>> {
        return storageManager.getStringFromAsssets("main/mainModules.json")
            .observeOn(Schedulers.computation())
            .map {
                Gson().fromJson<List<MainModuleModel>>(it, object : TypeToken<List<MainModuleModel>>() {}.type)
            }

    }
    
    
    
}