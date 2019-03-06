package ywxt.myswjtu.ui.main.main

import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import ywxt.myswjtu.R
import ywxt.myswjtu.common.ui.BaseFragment
import ywxt.myswjtu.databinding.FragmentMainBinding


class MainFragment : BaseFragment<FragmentMainBinding,MainViewModel>() {
    override val viewModel: MainViewModel by instance()
    override val layoutId: Int= R.layout.fragment_main

    override fun bindViewModel(dataBinding: FragmentMainBinding?) {
        dataBinding?.vm=viewModel
    }

    override val kodein: Kodein= Kodein.lazy { 
        extend(parentKodein)
        import(mainModule)
    }
    
  

}
