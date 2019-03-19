package ywxt.myswjtu.ui.main.me

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import ywxt.myswjtu.common.ui.BaseFragment
import ywxt.myswjtu.common.viewmodels.BaseFragmentViewModel
import ywxt.myswjtu.managers.ToastManager
import ywxt.myswjtu.managers.UserManager
import ywxt.myswjtu.models.UserModel

class MeViewModel(fragment: BaseFragment) : BaseFragmentViewModel(fragment) {
    private val userManager: UserManager by instance()
    private val toastManager: ToastManager by instance()

    val user: MutableLiveData<UserModel> = MutableLiveData()

    init {
        getUser()
    }

    fun getUser() {
        userManager.getUser()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                user.value = it
            }, {
                toastManager.toast(it.localizedMessage)
            })
    }
}