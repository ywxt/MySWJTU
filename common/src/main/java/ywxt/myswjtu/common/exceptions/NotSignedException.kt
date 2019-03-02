package ywxt.myswjtu.common.exceptions

import java.lang.RuntimeException

class NotSignedException(val msg:String):RuntimeException(msg)