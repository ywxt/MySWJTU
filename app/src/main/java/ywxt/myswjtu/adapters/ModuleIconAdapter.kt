package ywxt.myswjtu.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ywxt.myswjtu.R
import ywxt.myswjtu.databinding.AdapterModuleBinding
import ywxt.myswjtu.models.MainModuleModel

class ModuleIconAdapter(private val moduleList: List<MainModuleModel>) :
    RecyclerView.Adapter<ModuleIconAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_module, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = moduleList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewModel(moduleList[position])
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: AdapterModuleBinding? = DataBindingUtil.bind(itemView)

        fun bindViewModel(mainModuleModel: MainModuleModel) {
            binding?.vm = mainModuleModel
        }
    }

}