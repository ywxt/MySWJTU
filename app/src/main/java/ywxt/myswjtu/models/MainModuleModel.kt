package ywxt.myswjtu.models

import ywxt.myswjtu.ui.modules.ModuleViewModel

data class MainModuleModel(
    val text:String,
    val image:String,
    val path:String,
    val pages:List<ModuleViewModel>
)