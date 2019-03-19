package ywxt.myswjtu.ui.modules

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_module.*
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import ywxt.myswjtu.R
import ywxt.myswjtu.common.adapters.FragmentAdapter
import ywxt.myswjtu.common.ui.BaseFragment
import ywxt.myswjtu.common.ui.DataBindingActivity
import ywxt.myswjtu.databinding.ActivityModuleBinding
import ywxt.myswjtu.managers.ConfigurationManager
import ywxt.myswjtu.modules.PATH_ROUTE_MODULE

@Route(path = PATH_ROUTE_MODULE)
class ModuleActivity : DataBindingActivity<ActivityModuleBinding, ModuleViewModel>() {
    override val viewModel: ModuleViewModel by instance()
    override val layoutId: Int = R.layout.activity_module
    @JvmField
    @Autowired
    var path: String = ""
    override val kodein: Kodein by Kodein.lazy {
        extend(parentKodein)
        import(mainModulesModule)
    }
    private val router by instance<ARouter>()
    private val fragments: MutableList<BaseFragment> by instance()
    private val adapter: FragmentAdapter by instance()

    override fun bindViewModel(dataBinding: ywxt.myswjtu.databinding.ActivityModuleBinding) {
        this.setSupportActionBar(dataBinding.modulesToolbar)
        this.supportActionBar?.setHomeButtonEnabled(true)
        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initFragment(viewModel, adapter)
        dataBinding.viewPagerModule.adapter = adapter
        viewModel.initData(path)
        viewModel.currentModel.observe(this, Observer {
            dataBinding.tabModule.setupWithViewPager(dataBinding.viewPagerModule)
            it.pages.forEachIndexed { index, s ->
                dataBinding.tabModule.getTabAt(index)?.text = s.text
            }
        })
        dataBinding.vm = viewModel

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        router.inject(this)
        super.onCreate(savedInstanceState)

    }

    private fun initFragment(viewModel: ModuleViewModel, adapter: FragmentAdapter) {
        viewModel.currentModel.observe(this, Observer { vm ->
            fragments.clear()
            vm.pages.forEach {
                fragments.add(router.build(it.path).navigation() as BaseFragment)
            }
            adapter.notifyDataSetChanged()

        })
    }
}
