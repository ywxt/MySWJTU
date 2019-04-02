package ywxt.myswjtu.models

data class UserModel (
    val number:String,
    val name:String,
    val image:String?,
    val email:String?,
    val qq:String?,
    val mobilePhone:String?,
    var cookie:String
)