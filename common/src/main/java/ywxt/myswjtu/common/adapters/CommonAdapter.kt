package ywxt.myswjtu.common.adapters

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class CommonAdapter<TViewModel,TDataBinding: ViewDataBinding>(
    private val viewModels: List<TViewModel>
    ) :RecyclerView.Adapter<CommonAdapter.ViewHolder<TViewModel, TDataBinding>>() {
    
    override fun getItemCount(): Int = viewModels.size

    override fun onBindViewHolder(holder: ViewHolder<TViewModel,TDataBinding>, position: Int) {
        holder.bindViewModel(viewModels[position])
    }

    abstract class ViewHolder<TViewModel,TDataBinding:ViewDataBinding>(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding: TDataBinding? = DataBindingUtil.bind(itemView)

        abstract fun bindViewModel(viewModel: TViewModel)
    }

}
