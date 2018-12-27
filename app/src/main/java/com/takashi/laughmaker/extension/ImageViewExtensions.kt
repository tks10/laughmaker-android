package com.takashi.laughmaker.extension

import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.widget.ImageView

@BindingAdapter("bitmapImage")
fun ImageView.bitmapImage(bitmap: Bitmap) {
    this.setImageBitmap(bitmap)
}
