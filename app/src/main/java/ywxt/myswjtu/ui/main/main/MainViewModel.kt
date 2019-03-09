package ywxt.myswjtu.ui.main.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import ywxt.myswjtu.common.viewmodels.BaseViewModel
import ywxt.myswjtu.managers.ConfigurationManager
import ywxt.myswjtu.models.MainModuleModel

class MainViewModel(application: Application):BaseViewModel(application) {
    override val kodein: Kodein=parentKodein
    private val configurationManager:ConfigurationManager by instance()
    val moduleList:MutableLiveData<List<MainModuleModel>> = MutableLiveData()
    init {
        initModules()
    }

    /**
     * 加载模块
     */
    fun initModules(){
        configurationManager.getMainModules()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { 
                moduleList.value=it
            }
    }
}