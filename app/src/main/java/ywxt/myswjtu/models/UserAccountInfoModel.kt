package ywxt.myswjtu.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root


@Root(name = "modelist", strict = false)
data class UserAccountInfoModel(
    @field:Element
    var selectState: Int,
    @field:Element
    var setAction: String,
    @field:Element(data = true,required = false)
    var email: String?,
    @field:Element(data = true,required = false)
    var qq: String?,
    @field:Element(data = true,required = false)
    var weixinId: String?,
    @field:Element(data = true,required = false)
    var mobilePhone: String?,
    @field:Element(data = true,required = false)
    var description: String?,
    @field:Element(data = true,required = false)
    var createTime: String,
    @field:Element(data = true,required = false)
    var lastLoginTime: String,
    @field:Element(data = true,required = false)
    var lastLoginIp: String,
    @field:Element(data = true,required = false)
    var lastLoginArea: String?,
    @field:Element(data = true,required = false)
    var question: String?,
    @field:Element(data = true,required = false)
    var answer: String?,
    @field:Element(data = true,required = false)
    var userImg: String?
) {
    constructor() : this(
        0, 
        "", 
        null, 
        null, 
        null,
        null, 
        null, 
        "", 
        "", 
        "", 
        null, 
        null, 
        null, 
        null
    )
}