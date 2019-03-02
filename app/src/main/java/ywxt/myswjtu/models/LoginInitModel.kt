package ywxt.myswjtu.models

data class LoginInitModel (
    
    val universityId: String,

    val success: Int,

    val checkPower: String,

    val challenge: String,

    val userType: String,

    val gt: String
)
   
