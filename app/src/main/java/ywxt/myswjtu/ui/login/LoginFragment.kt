package ywxt.myswjtu.ui.login

import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import ywxt.myswjtu.R
import ywxt.myswjtu.common.ui.DataBindingFragment
import ywxt.myswjtu.databinding.FragmentLoginBinding
import ywxt.myswjtu.modules.PATH_ROUTE_LOGIN_FRAGMENT

@Route(path = PATH_ROUTE_LOGIN_FRAGMENT)
class LoginFragment : DataBindingFragment<FragmentLoginBinding, LoginViewModel>() {
    override val layoutId: Int = R.layout.fragment_login

    override val viewModel: LoginViewModel by instance()
    override val kodein: Kodein = Kodein.lazy {
        extend(parentKodein)
        import(loginModule)
    }


    override fun bindViewModel(dataBinding: FragmentLoginBinding?) {
        if (dataBinding == null) return
        dataBinding.vm = viewModel
        viewModel.username.observe(this.activity!!, Observer<String> {
            viewModel.checkUsername()
        })
        viewModel.password.observe(this.activity!!, Observer<String> {
            viewModel.checkPassword()
        })
        
    }


}