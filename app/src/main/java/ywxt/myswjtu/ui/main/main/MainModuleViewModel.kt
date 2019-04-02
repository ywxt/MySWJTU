package ywxt.myswjtu.ui.main.main

import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.launcher.ARouter
import ywxt.myswjtu.common.viewmodels.BaseViewModel
import ywxt.myswjtu.modules.PATH_ROUTE_MODULE

class MainModuleViewModel(
    private val router:ARouter
) : BaseViewModel() {
    val image: MutableLiveData<String> = MutableLiveData()
    val text: MutableLiveData<String> = MutableLiveData()
    val path: MutableLiveData<String> = MutableLiveData()
    

    fun navigate2Module() {
        router.build(PATH_ROUTE_MODULE)
            .withString("path",path.value)
            .navigation()
    }
}