package com.takashi.laughmaker.extension

import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.widget.ImageView

@BindingAdapter("android:bitmap_image")
fun ImageView.bitmap_image(bitmap: Bitmap) {
    this.setImageBitmap(bitmap)
}
