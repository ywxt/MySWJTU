package ywxt.myswjtu.common.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ywxt.myswjtu.common.ui.BaseFragment

class FragmentViewModelFactory(private val fragment: BaseFragment):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(BaseFragment::class.java).newInstance(fragment)
}