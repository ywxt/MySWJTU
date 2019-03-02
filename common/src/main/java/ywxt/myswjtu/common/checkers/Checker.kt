package ywxt.myswjtu.common.checkers

import android.content.Context

abstract class Checker(private val context: Context) {

    fun getString(id: Int): String = context.getString(id)
    fun getString(id: Int, vararg param: Any) = context.getString(id, param)
}