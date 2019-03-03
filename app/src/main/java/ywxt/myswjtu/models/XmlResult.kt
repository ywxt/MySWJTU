package ywxt.myswjtu.models

import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "modelist")
data class XmlResult<T> (
    @XmlElement
    val selectState:Int,
    @XmlElement
    val setAction:Int,
    val data:T
)