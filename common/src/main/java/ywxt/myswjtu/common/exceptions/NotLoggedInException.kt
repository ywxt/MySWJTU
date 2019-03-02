package ywxt.myswjtu.common.exceptions

import java.lang.RuntimeException

class NotLoggedInException(val msg:String):RuntimeException(msg)