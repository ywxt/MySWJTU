package ywxt.myswjtu.common.ui

import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import ywxt.myswjtu.common.enums.ViewState

abstract class BaseActivity : AppCompatActivity(), KodeinAware {
    protected val parentKodein by kodein()
    
    private val popupWindow by lazy { PopupWindow(this) }
    var viewState: ViewState = ViewState.OK
        private set(value) {
            field = value
        }

    fun setLoadingViewState(
        layout: Int,
        anim: Int = 0,
        alpha: Float = 0.8F,
        cancelable: Boolean = false
    ) {
        val view = LayoutInflater.from(this).inflate(layout, null)
        view.alpha = alpha
        popupWindow.apply {
            contentView = view
            if (anim != 0) animationStyle = anim
            isFocusable = true
            setBackgroundDrawable(ColorDrawable(0x000000))
            isOutsideTouchable = cancelable
            isTouchable = true
            width = WindowManager.LayoutParams.MATCH_PARENT
            height = WindowManager.LayoutParams.MATCH_PARENT
        }
        viewState = ViewState.LOADING
        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.CENTER, 0, 0)
       
    }

    fun setOkViewState() {
        when (viewState) {
            ViewState.OK -> {
            }
            ViewState.ERROR -> {
               
            }
            ViewState.LOADING -> {
                popupWindow.dismiss()
                
            }
            ViewState.NETWORK_ERROR -> {
                
            }
        }
        viewState = ViewState.OK
    }
    
}
