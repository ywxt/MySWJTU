package ywxt.myswjtu.ui.main.main

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import ywxt.myswjtu.R
import ywxt.myswjtu.adapters.MainModuleAdapter
import ywxt.myswjtu.common.ui.DataBindingFragment
import ywxt.myswjtu.databinding.FragmentMainBinding
import ywxt.myswjtu.modules.PATH_ROUTE_MAIN_MAIN

@Route(path = PATH_ROUTE_MAIN_MAIN)
class MainFragment : DataBindingFragment<FragmentMainBinding, MainViewModel>() {
    override val viewModel: MainViewModel by instance()
    override val layoutId: Int = R.layout.fragment_main
    private val router by instance<ARouter>()
    private val adapterViewModels by instance<MutableList<MainModuleViewModel>>()
    private val adapter by instance<MainModuleAdapter>()
    override val kodein: Kodein = Kodein.lazy {
        extend(parentKodein)
        import(mainModule)
    }

    override fun bindViewModel(dataBinding: FragmentMainBinding?) {
        if (dataBinding == null) return
        dataBinding.vm = viewModel
        val modulesView=dataBinding.modulesView
        viewModel.moduleList.observe(this, Observer {
            adapterViewModels.clear()
            it.forEach {
                val vm = MainModuleViewModel(router)
                vm.apply {
                    text.value = it.text
                    image.value = it.image
                    path.value = it.path
                }
                adapterViewModels.add(vm)
            }
            modulesView.adapter = adapter
        })
        modulesView.layoutManager = GridLayoutManager(this.context, 5)
    }


}
