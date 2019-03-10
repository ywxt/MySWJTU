package ywxt.myswjtu.common.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import org.kodein.di.KodeinAware

abstract class DataBindingActivity<T:ViewDataBinding,E:ViewModel>: BaseActivity(), KodeinAware {
    
    
    abstract val viewModel:E

    abstract val layoutId:Int

    abstract fun bindViewModel(dataBinding: T)
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding=DataBindingUtil.setContentView<T>(this,layoutId)
        bindViewModel(dataBinding)
    }
    
}