package ywxt.myswjtu.models

data class UserAccountInfoModel(
    val selectState:Int,
    val setAction:String,
    val email:String?,
    val qq:String?,
    val weixinId:String?,
    val mobilePhone:String?,
    val description:String?,
    val createTime:String,
    val lastLoginTime:String,
    val lastLoginIp:String,
    val lastLoginArea:String?,
    val question:String?,
    val answer:String?,
    val userImg:String?
)