package ywxt.myswjtu.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "modelist",strict = false)
data class DateInfoModel(
    @field:Element(data = true,required = false)
    var onlineStatus:Int,
    @field:Element(data = true,required = false)
    var weekDay:String,
    @field:Element(data = true,required = false)
    var thisDate:String,
    @field:Element(data = true,required = false)
    var weekTeach:Int

){
    constructor():this(0,"","",0)
}