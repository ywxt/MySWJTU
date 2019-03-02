package ywxt.myswjtu.common.enums

import ywxt.myswjtu.common.R

enum class UserNameError(val message:Int) {
    LENGTH_TOO_LONG(R.string.username_length_too_long),
    LENGTH_TOO_SHORT(R.string.username_length_too_short)
    
}