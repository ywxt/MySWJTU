package ywxt.myswjtu.ui.modules.course.timetable

import com.alibaba.android.arouter.facade.annotation.Route
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import ywxt.myswjtu.common.ui.BaseFragment
import ywxt.myswjtu.modules.PATH_ROUTE_MODULE_TIMETABLE

@Route(path = PATH_ROUTE_MODULE_TIMETABLE)
class TimetableFragment:BaseFragment() {
    override val kodein: Kodein by instance()
}