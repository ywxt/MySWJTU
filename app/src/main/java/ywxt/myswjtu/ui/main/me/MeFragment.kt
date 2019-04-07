package ywxt.myswjtu.ui.main.me

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import ywxt.myswjtu.R
import ywxt.myswjtu.common.ui.DataBindingFragment
import ywxt.myswjtu.managers.TimetableConfiguration
import ywxt.myswjtu.modules.PATH_ROUTE_MAIN_ME
import ywxt.myswjtu.ui.main.setting.SettingFragment


@Route(path = PATH_ROUTE_MAIN_ME)
class MeFragment : DataBindingFragment<ywxt.myswjtu.databinding.FragmentMeBinding, MeViewModel>() {
    override val viewModel: MeViewModel by instance()
    override val layoutId: Int = R.layout.fragment_me
    private val router: ARouter by instance()
    private val configuration: TimetableConfiguration by instance()

    override fun bindViewModel(dataBinding: ywxt.myswjtu.databinding.FragmentMeBinding?) {
        dataBinding?.vm = viewModel
        viewModel.image.observe(this, Observer {
            Glide.with(this.context!!)
                .load(it)
                .placeholder(R.drawable.ic_person)
                .error(R.drawable.ic_person)
                .into(dataBinding?.imageHead!!)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val settingFragment = router.build(PATH_ROUTE_ME_SETTING).navigation() as Fragment
        val settingFragment = SettingFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.setting_container, settingFragment)
            .commit()
    }

    override val kodein: Kodein = Kodein.lazy {
        extend(parentKodein)
        import(meModule)
    }


}
