package ywxt.myswjtu.common.exceptions

import java.lang.RuntimeException

class ParamInitException(val msg: String, val param: String) : RuntimeException(msg){
    constructor(msg: String):this(msg,"")
}