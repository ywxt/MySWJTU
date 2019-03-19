package ywxt.myswjtu.common.viewmodels

import androidx.lifecycle.ViewModel
import org.kodein.di.generic.instance
import ywxt.myswjtu.common.ui.BaseActivity

abstract class BaseActivityViewModel(protected val activity:BaseActivity):ViewModel() {
    
    protected inline fun <reified T : Any> instance(tag: String? = null) = activity.instance<T>(tag)
}