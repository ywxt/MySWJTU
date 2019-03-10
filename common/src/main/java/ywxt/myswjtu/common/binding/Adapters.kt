package ywxt.myswjtu.common.binding

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("android:imageSrc")
fun setImageViewImage(imageView: ImageView,bitmap: Bitmap?){
    imageView.setImageBitmap(bitmap)
}
@BindingAdapter("android:errorMessage")
fun setTextInputLayoutError(layout: TextInputLayout,message:String? ){
    layout.error=message
}

@BindingAdapter("android:imageUrl","android:error","android:placeholder")
fun setImageViewImage(imageView: ImageView,imageUrl:String,error: Drawable,placeholder:Drawable){
    Glide.with(imageView.context)
        .load(imageUrl)
        .placeholder(placeholder)
        .error(error)
        .into(imageView)
}
@BindingAdapter("android:associatedViewPager")
fun setTabLayoutAssociatedViewPager(tabLayout: TabLayout,viewPager: Int){
    val vp=tabLayout.findViewById<ViewPager>(viewPager)
    tabLayout.setupWithViewPager(vp)
}