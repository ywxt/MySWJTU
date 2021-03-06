package ywxt.myswjtu.ui.main.me

import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import ywxt.myswjtu.common.ui.BaseFragment
import ywxt.myswjtu.common.viewmodels.BaseFragmentViewModel
import ywxt.myswjtu.managers.CookieManager
import ywxt.myswjtu.managers.TimetableConfiguration
import ywxt.myswjtu.managers.ToastManager
import java.io.FileNotFoundException

class MeViewModel(fragment: BaseFragment) : BaseFragmentViewModel(fragment) {
    private val dataSource: MeDataSource by instance()
    private val toastManager: ToastManager by instance()
    private val timetableConfiguration: TimetableConfiguration by instance()
    private val cookieManager: CookieManager by instance()
    val number: MutableLiveData<String> = MutableLiveData()
    val name: MutableLiveData<String> = MutableLiveData()
    val image: MutableLiveData<String> = MutableLiveData()

    init {
        getUser()
    }

    fun getUser() {
        Flowable.concatArrayDelayError(
            dataSource.getLocalUser(),
            dataSource.getRemoteUser()
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                number.value = it.number
                name.value = it.name
                image.value = it.image
            }, {
                when (it) {
                    is FileNotFoundException -> {
                    }
                    else -> toastManager.toast(it.localizedMessage)
                }

            })
    }

    override fun onCleared() {
        super.onCleared()
        timetableConfiguration.saveTimetableConfiguration()
        cookieManager.saveCookie()
        Log.i("MeViewModel","Clear")
    }
}