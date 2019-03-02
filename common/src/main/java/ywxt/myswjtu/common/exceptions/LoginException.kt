package ywxt.myswjtu.common.exceptions

import java.lang.RuntimeException

class LoginException(message: String?,val code:Int) : RuntimeException(message) 