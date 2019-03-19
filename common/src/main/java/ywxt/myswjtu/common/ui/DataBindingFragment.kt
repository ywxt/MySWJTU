package ywxt.myswjtu.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class DataBindingFragment<T: ViewDataBinding,E: ViewModel>:BaseFragment() {
    abstract val viewModel:E
    abstract val layoutId:Int

    abstract fun bindViewModel(dataBinding: T?)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bind= DataBindingUtil.bind<T>(view)
        bind?.apply {
            bindViewModel(bind)
            lifecycleOwner=this@DataBindingFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        LayoutInflater.from(this.context).inflate(layoutId,container,false)
}