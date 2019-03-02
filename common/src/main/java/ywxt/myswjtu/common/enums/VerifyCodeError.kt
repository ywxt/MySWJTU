package ywxt.myswjtu.common.enums

import ywxt.myswjtu.common.R

enum class VerifyCodeError(val message:Int) {
    LENGTH_TOO_LONG(R.string.verify_code_length_too_long),
    LENGTH_TOO_SHORT(R.string.verify_code__length_too_short)
}