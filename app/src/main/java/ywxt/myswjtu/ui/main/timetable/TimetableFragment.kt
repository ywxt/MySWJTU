package ywxt.myswjtu.ui.main.timetable

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import ywxt.myswjtu.R
import ywxt.myswjtu.common.ui.DataBindingFragment
import ywxt.myswjtu.databinding.FragmentTimetableBinding
import ywxt.myswjtu.managers.TimetableConfiguration
import ywxt.myswjtu.modules.PATH_ROUTE_MAIN_TIMETABLE

@Route(path = PATH_ROUTE_MAIN_TIMETABLE)
class TimetableFragment : DataBindingFragment<FragmentTimetableBinding, TimetableViewModel>() {
    override val viewModel: TimetableViewModel by instance()
    override val layoutId: Int = R.layout.fragment_timetable
    private val configuration: TimetableConfiguration by instance()
    val dataSource:TimetableDataSource by instance()
    
    override fun bindViewModel(dataBinding: FragmentTimetableBinding?) {
        if (dataBinding == null) return
        dataBinding.vm = viewModel
        viewModel.timetable.observe(this, Observer { list ->
            dataBinding.idWeekview.source(list.filterNot { it.day == 0 || it.step == 0 })
                .hideLeftLayout()
                .itemCount(23)
                .callback { week ->  
                    dataBinding.idTimetableView.onDateBuildListener().onUpdateDate(viewModel.currentWeek.value?:0,week)
                    dataBinding.idTimetableView.changeWeekOnly(week)
                }
                .showView()
            dataBinding.idTimetableView
                .source(list.filterNot { it.day == 0 || it.step == 0 })
                .maxSlideItem(13)
                .showView()
        })
        viewModel.currentWeek.observe(this, Observer { 
            dataBinding.idTimetableView.curWeek(it).updateView()
            dataBinding.idWeekview.curWeek(it).updateView()
        })
        configuration.showAllCourse.observe(this, Observer { 
            dataBinding.idTimetableView.isShowNotCurWeek(it)
                .updateView()
        })
        configuration.showWeekend.observe(this, Observer { 
            dataBinding.idTimetableView.isShowWeekends(it)
                .updateView()
        })
    }

    override val kodein: Kodein = Kodein.lazy {
        extend(parentKodein)
        import(timetableModule)
    }
    
    inner class TimetableViewModelFactory:ViewModelProvider.Factory{
        
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(TimetableDataSource::class.java).newInstance(dataSource)
        }

    }

    
}
