package ywxt.myswjtu.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root


@Root(name = "modelist",strict = false)
data class XmlResult<T> (
    @Element
    val selectState:Int,
    @Element
    val setAction:Int,
    val data:T
)