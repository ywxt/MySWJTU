package ywxt.myswjtu.common.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ywxt.myswjtu.common.ui.BaseActivity
import ywxt.myswjtu.common.ui.BaseFragment

class ActivityViewModelFactory(private val activity: BaseActivity): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(BaseActivity::class.java).newInstance(activity)
}