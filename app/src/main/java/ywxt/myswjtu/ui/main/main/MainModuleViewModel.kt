package ywxt.myswjtu.ui.main.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.launcher.ARouter
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import ywxt.myswjtu.common.viewmodels.BaseViewModel
import ywxt.myswjtu.models.MainModuleModel
import ywxt.myswjtu.modules.PATH_ROUTE_MODULE

class MainModuleViewModel(application: Application) : BaseViewModel(application) {
    override val kodein: Kodein = parentKodein
    val image: MutableLiveData<String> = MutableLiveData()
    val text: MutableLiveData<String> = MutableLiveData()
    val path: MutableLiveData<String> = MutableLiveData()
    private val router by instance<ARouter>()

    fun navigate2Module() {
        router.build(PATH_ROUTE_MODULE)
            .withString("path",path.value)
            .navigation()
    }
}