package ywxt.myswjtu.managers

import android.content.Context
import android.widget.Toast

class ToastManager(private val context: Context) {
    
    fun longToast(message:String){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }
    fun toast(message: String){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }
}