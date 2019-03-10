package ywxt.myswjtu.ui.modules

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import ywxt.myswjtu.R
import ywxt.myswjtu.common.ui.BaseActivity
import ywxt.myswjtu.common.ui.DataBindingActivity
import ywxt.myswjtu.models.MainModuleModel
import ywxt.myswjtu.modules.PATH_ROUTE_MODULE

@Route(path = PATH_ROUTE_MODULE)
class ModuleActivity : DataBindingActivity<ywxt.myswjtu.databinding.ActivityModuleBinding, ModuleViewModel>() {
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

    override fun bindViewModel(dataBinding: ywxt.myswjtu.databinding.ActivityModuleBinding) {
        this.setSupportActionBar(dataBinding.modulesToolbar)
        this.supportActionBar?.setHomeButtonEnabled(true)
        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        dataBinding.vm = viewModel
        viewModel.initData(path)


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        router.inject(this)
        super.onCreate(savedInstanceState)

        initFragment()
    }

    private fun initFragment() {
//        supportFragmentManager.apply {
//            findFragmentByTag(path) ?: beginTransaction().replace(
//                R.id.modules_fragment,
//                router.build(path).navigation() as Fragment,
//                path
//            ).commitAllowingStateLoss()
//
//        }
//    }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
