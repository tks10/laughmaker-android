package com.takashi.laughmaker.extension

import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.widget.ImageView

@BindingAdapter("android:bitmap")
fun ImageView.bitmap(bitmap: Bitmap) {
    this.setImageBitmap(bitmap)
}
