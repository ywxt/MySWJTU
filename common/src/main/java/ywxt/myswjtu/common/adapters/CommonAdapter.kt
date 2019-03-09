package ywxt.myswjtu.common.adapters

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

abstract class CommonAdapter<TViewModel:ViewModel,TDataBinding: ViewDataBinding>(
    private val viewModels: List<TViewModel>
    ) :RecyclerView.Adapter<CommonAdapter.ViewHolder<TViewModel, TDataBinding>>() {
    
    override fun getItemCount(): Int = viewModels.size

    override fun onBindViewHolder(holder: ViewHolder<TViewModel,TDataBinding>, position: Int) {
        holder.bindViewModel(viewModels[position])
    }

    abstract class ViewHolder<TViewModel:ViewModel,TDataBinding:ViewDataBinding>(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding: TDataBinding? = DataBindingUtil.bind(itemView)

        abstract fun bindViewModel(viewModel: TViewModel)
    }

}
