package ywxt.myswjtu.common.ui

import androidx.fragment.app.Fragment
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein

abstract class BaseFragment : Fragment(), KodeinAware {

    protected val parentKodein by kodein()
    

}

