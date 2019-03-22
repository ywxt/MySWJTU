package ywxt.myswjtu.ui.main.timetable

import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import ywxt.myswjtu.R
import ywxt.myswjtu.common.ui.DataBindingFragment
import ywxt.myswjtu.databinding.FragmentTimetableBinding
import ywxt.myswjtu.modules.PATH_ROUTE_MAIN_TIMETABLE

@Route(path = PATH_ROUTE_MAIN_TIMETABLE)
class TimetableFragment : DataBindingFragment<FragmentTimetableBinding,TimetableViewModel>() {
    override val viewModel: TimetableViewModel by instance()
    override val layoutId: Int = R.layout.fragment_timetable

    override fun bindViewModel(dataBinding: FragmentTimetableBinding?) {
        if (dataBinding==null) return
        dataBinding.vm=viewModel
        viewModel.timetable.observe(this, Observer{
            dataBinding.idTimetableView.source(it)
        })
    }

    override val kodein: Kodein = Kodein.lazy { 
        extend(parentKodein)
        import(timetableModule)
    }

    

}
