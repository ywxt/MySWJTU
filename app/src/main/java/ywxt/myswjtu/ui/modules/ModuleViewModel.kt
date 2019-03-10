package ywxt.myswjtu.ui.modules

import android.app.Application
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import ywxt.myswjtu.common.viewmodels.BaseViewModel
import ywxt.myswjtu.managers.ConfigurationManager
import ywxt.myswjtu.models.MainModuleModel

class ModuleViewModel(application: Application) : BaseViewModel(application) {
    override val kodein: Kodein = parentKodein
    private val configurationManager by instance<ConfigurationManager>()
    val currentModule: MutableLiveData<MainModuleModel> = MutableLiveData()
    val pageList:MutableLiveData<List<String>> = MutableLiveData()
    

    fun initData(path: String) {
        configurationManager.getMainModules()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list ->
                val model = list.single { it.path == path }
                currentModule.value = model
            }
    }

}