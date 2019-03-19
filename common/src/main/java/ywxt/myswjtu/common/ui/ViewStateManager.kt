package ywxt.myswjtu.common.ui

import ywxt.myswjtu.common.R
import ywxt.myswjtu.common.enums.ViewState

class ViewStateManager(private val activity: BaseActivity) {
    val viewState: ViewState = activity.viewState
    fun setLoadingViewState(
        layout: Int,
        anim: Int = 0,
        alpha: Float = 0.8F,
        cancelable: Boolean = false
    ) {
        activity.setLoadingViewState(layout, anim, alpha, cancelable)
    }

    fun setLoadingViewState(
        anim: Int = 0,
        alpha: Float = 0.8F,
        cancelable: Boolean = false
    ) {
        setLoadingViewState(R.layout.layout_loading,anim, alpha, cancelable)
    }
    
    fun setOkViewState(){
        activity.setOkViewState()
    }

}