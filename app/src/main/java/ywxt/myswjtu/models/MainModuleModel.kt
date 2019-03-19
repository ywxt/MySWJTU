package ywxt.myswjtu.models

data class MainModuleModel(
    val text:String,
    val image:String,
    val path:String,
    val pages:List<ModulePageModel>
)