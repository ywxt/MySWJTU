package ywxt.myswjtu.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ywxt.myswjtu.R
import ywxt.myswjtu.common.adapters.CommonAdapter
import ywxt.myswjtu.databinding.AdapterModuleBinding
import ywxt.myswjtu.ui.main.main.MainModuleViewModel

class MainModuleAdapter(viewModels: List<MainModuleViewModel>) :
    CommonAdapter<MainModuleViewModel, AdapterModuleBinding>(viewModels) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<MainModuleViewModel, AdapterModuleBinding> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_module, parent, false)
        return MainModuleViewHolder(view)
    }

    class MainModuleViewHolder(view: View) : CommonAdapter.ViewHolder<MainModuleViewModel, AdapterModuleBinding>(view) {
        override fun bindViewModel(viewModel: MainModuleViewModel) {
            binding?.vm = viewModel
        }


    }
}

