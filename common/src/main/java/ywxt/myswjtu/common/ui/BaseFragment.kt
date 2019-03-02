package ywxt.myswjtu.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.support.kodein

abstract class BaseFragment<T:ViewDataBinding,E:ViewModel>:Fragment(),KodeinAware {
    
    protected val parentKodein by kodein()
    
    abstract val viewModel:E
    
    abstract val layoutId:Int
    
    abstract fun bindViewModel(dataBinding: T?)
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bind=DataBindingUtil.bind<T>(view)
        bind?.apply { 
            bindViewModel(bind)
            lifecycleOwner=this@BaseFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        LayoutInflater.from(this.context).inflate(layoutId,container,false)
}