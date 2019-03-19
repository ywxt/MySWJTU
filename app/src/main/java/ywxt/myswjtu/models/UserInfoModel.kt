package ywxt.myswjtu.models

import me.ghui.fruit.annotations.Pick

data class UserInfoModel(
    @Pick("#ChatMain > div.infoMain.clearfix > div.right-info-box > table:nth-child(1) > tbody > tr > td:nth-child(2)")
    val number: String = "",
    @Pick("#ChatMain > div.infoMain.clearfix > div.right-info-box > table:nth-child(1) > tbody > tr > td:nth-child(4)")
    val name: String = ""
)