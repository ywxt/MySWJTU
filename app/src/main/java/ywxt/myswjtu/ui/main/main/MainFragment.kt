package ywxt.myswjtu.ui.main.main

import android.app.Application
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import ywxt.myswjtu.R
import ywxt.myswjtu.adapters.MainModuleAdapter
import ywxt.myswjtu.common.ui.BaseFragment
import ywxt.myswjtu.databinding.FragmentMainBinding
import ywxt.myswjtu.modules.PATH_ROUTE_MAIN_MAIN

@Route(path = PATH_ROUTE_MAIN_MAIN)
class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {
    override val viewModel: MainViewModel by instance()
    override val layoutId: Int = R.layout.fragment_main
    private val application by instance<Application>()
    private val adapterViewModels by instance<MutableList<MainModuleViewModel>>()
    override val kodein: Kodein = Kodein.lazy {
        extend(parentKodein)
        import(mainModule)
    }

    override fun bindViewModel(dataBinding: FragmentMainBinding?) {
        if (dataBinding == null) return
        dataBinding.vm = viewModel

        viewModel.moduleList.observe(this, Observer {
            if (!adapterViewModels.isNotEmpty()) return@Observer
            it.forEach {
                val vm = MainModuleViewModel(application)
                vm.apply {
                    text.value = it.text
                    image.value = it.image
                    path.value = it.path
                }
                adapterViewModels.add(vm)
            }
        })
        dataBinding.modulesView.adapter = MainModuleAdapter(adapterViewModels)
        dataBinding.modulesView.layoutManager = GridLayoutManager(this.context, 5)
    }


}
