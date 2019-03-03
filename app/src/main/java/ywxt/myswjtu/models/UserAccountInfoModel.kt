package ywxt.myswjtu.models

import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "modelist")
data class UserAccountInfoModel(
    @XmlElement
    val selectState:Int,
    @XmlElement
    val setAction:String,
    @XmlElement
    val email:String?,
    @XmlElement
    val qq:String?,
    @XmlElement
    val weixinId:String?,
    @XmlElement
    val mobilePhone:String?,
    @XmlElement
    val description:String?,
    @XmlElement
    val createTime:String,
    @XmlElement
    val lastLoginTime:String,
    @XmlElement
    val lastLoginIp:String,
    @XmlElement
    val lastLoginArea:String?,
    @XmlElement
    val question:String?,
    @XmlElement
    val answer:String?,
    @XmlElement
    val userImg:String?
)