package ywxt.myswjtu.models

import androidx.lifecycle.MutableLiveData

data class UserModel (
    val number:MutableLiveData<String>,
    val name:MutableLiveData<String>,
    val image:MutableLiveData<String>,
    val email:MutableLiveData<String>,
    val qq:MutableLiveData<String>,
    val mobilePhone:MutableLiveData<String>,
    var cookie:String
)