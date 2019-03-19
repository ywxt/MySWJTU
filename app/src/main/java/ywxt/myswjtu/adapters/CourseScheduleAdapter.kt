package ywxt.myswjtu.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import ywxt.myswjtu.R
import ywxt.myswjtu.common.adapters.CommonAdapter
import ywxt.myswjtu.databinding.AdapterCourseScheduleBinding
import ywxt.myswjtu.models.CourseModel

class CourseScheduleAdapter(viewmodels: List<CourseModel>) :
    CommonAdapter<CourseModel, AdapterCourseScheduleBinding>(viewmodels) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommonAdapter.ViewHolder<CourseModel, AdapterCourseScheduleBinding> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_course_schedule, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : CommonAdapter.ViewHolder<CourseModel, AdapterCourseScheduleBinding>(view) {
        override fun bindViewModel(viewModel: CourseModel) {
            binding?.vm = viewModel
        }

    }
}