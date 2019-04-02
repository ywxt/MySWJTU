package ywxt.myswjtu.common.binding

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.textfield.TextInputLayout
import com.zhuangfei.timetable.TimetableView
import com.zhuangfei.timetable.model.ScheduleEnable
import com.zhuangfei.timetable.view.WeekView

@BindingAdapter("android:imageSrc")
fun setImageViewImage(imageView: ImageView, bitmap: Bitmap?) {
    imageView.setImageBitmap(bitmap)
}

@BindingAdapter("android:errorMessage")
fun setTextInputLayoutError(layout: TextInputLayout, message: String?) {
    layout.error = message
}

@BindingAdapter("android:imageUrl", "android:error", "android:placeholder")
fun setImageViewImage(imageView: ImageView, imageUrl: String, error: Drawable, placeholder: Drawable) {
    Glide.with(imageView.context)
        .load(imageUrl)
        .placeholder(placeholder)
        .error(error)
        .into(imageView)
}

@BindingAdapter("android:associatedViewPager", "android:titles")
fun setTabLayoutAssociatedViewPager(tabLayout: TabLayout, viewPager: Int, titles: List<String>?) {
    val vp = tabLayout.findViewById<ViewPager>(viewPager)
    tabLayout.setupWithViewPager(vp)
    titles?.forEachIndexed { index, s ->
        tabLayout.getTabAt(index)?.text = s
    }
}

