package ywxt.myswjtu.common.viewmodels

import androidx.lifecycle.ViewModel
import org.kodein.di.generic.instance
import ywxt.myswjtu.common.ui.BaseFragment

abstract class BaseFragmentViewModel(protected val fragment: BaseFragment) : ViewModel() {
    protected inline fun <reified T : Any> instance(tag: String? = null) = fragment.instance<T>(tag)
}