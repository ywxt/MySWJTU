package ywxt.myswjtu.ui.main.main

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import ywxt.myswjtu.common.ui.BaseFragment
import ywxt.myswjtu.common.viewmodels.BaseFragmentViewModel
import ywxt.myswjtu.managers.ConfigurationManager
import ywxt.myswjtu.models.MainModuleModel

class MainViewModel(
    fragment: BaseFragment
) : BaseFragmentViewModel(fragment) {
    private val configurationManager: ConfigurationManager by instance()

    val moduleList: MutableLiveData<List<MainModuleModel>> = MutableLiveData()

    init {
        initModules()
    }

    /**
     * 加载模块
     */
    fun initModules() {
        configurationManager.getMainModules()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                moduleList.value = it
            }
    }
}