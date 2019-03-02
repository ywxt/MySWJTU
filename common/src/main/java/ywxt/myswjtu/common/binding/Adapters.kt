package ywxt.myswjtu.common.binding

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("bind:image")
fun setImageViewImage(imageView: ImageView,bitmap: Bitmap?){
    imageView.setImageBitmap(bitmap)
}
@BindingAdapter("bind:errorMessage")
fun setTextInputLayoutError(layout: TextInputLayout,message:String? ){
    layout.error=message
}