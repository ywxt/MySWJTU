package ywxt.myswjtu.ui.main.me

import com.alibaba.android.arouter.facade.annotation.Route
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import ywxt.myswjtu.R
import ywxt.myswjtu.common.ui.DataBindingFragment
import ywxt.myswjtu.modules.PATH_ROUTE_MAIN_ME


@Route(path = PATH_ROUTE_MAIN_ME)
class SettingFragment : DataBindingFragment<ywxt.myswjtu.databinding.FragmentMeBinding, MeViewModel>() {
    override val viewModel: MeViewModel by instance()
    override val layoutId: Int = R.layout.fragment_me

    override fun bindViewModel(dataBinding: ywxt.myswjtu.databinding.FragmentMeBinding?) {
        dataBinding?.vm = viewModel
    }

    override val kodein: Kodein = Kodein.lazy {
        extend(parentKodein)
        import(meModule)
    }


}
