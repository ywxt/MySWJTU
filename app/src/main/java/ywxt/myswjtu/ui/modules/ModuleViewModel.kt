package ywxt.myswjtu.ui.modules

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import org.kodein.di.generic.instance
import ywxt.myswjtu.common.ui.BaseActivity
import ywxt.myswjtu.common.viewmodels.BaseActivityViewModel
import ywxt.myswjtu.managers.ConfigurationManager
import ywxt.myswjtu.models.MainModuleModel

class ModuleViewModel(
    activity: BaseActivity
) : BaseActivityViewModel(activity) {

    val currentModel: MutableLiveData<MainModuleModel> = MutableLiveData()
    val title: MutableLiveData<String> = MutableLiveData()
    private val configurationManager: ConfigurationManager by instance()

    fun initData(path: String) {
        configurationManager.getMainModules()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list ->
                val model = list.single { it.path == path }
                currentModel.value = model
                title.value = model.text
            }
    }
}