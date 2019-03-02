package ywxt.myswjtu.checkers

import android.content.Context
import ywxt.myswjtu.common.checkers.Checker
import ywxt.myswjtu.common.enums.PasswordError
import ywxt.myswjtu.common.enums.UserNameError
import ywxt.myswjtu.common.enums.VerifyCodeError

class LoginChecker(context: Context):Checker(context) {
    fun checkUserName(username: String?): String? {
        return when {
            username==null || username.length < 10 -> getString(UserNameError.LENGTH_TOO_SHORT.message)
            username.length > 10 -> getString(UserNameError.LENGTH_TOO_LONG.message)
            else -> null
        }
    }
    fun checkPassword(password:String?):String?{
        return when{
            password==null || password.length < 6 -> getString(PasswordError.LENGTH_TOO_SHORT.message)
            else -> null
        }
    }
    fun checkVerifyCode(code:String?):String?{
        return when{
            code==null || code.length < 4 -> getString(VerifyCodeError.LENGTH_TOO_SHORT.message)
            code.length > 4 -> getString(VerifyCodeError.LENGTH_TOO_LONG.message)
            else -> null
        }
    }
}