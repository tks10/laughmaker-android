package com.takashi.laughmaker.extension

import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.view.ViewGroup
import android.widget.ImageView
import kotlin.math.max

@BindingAdapter("bitmapImage")
fun ImageView.bitmapImage(bitmap: Bitmap) {
    val CLIP_SIZE = 512
    val height = bitmap.height
    val width = bitmap.width
    val maxSize = max(height, width)
    val resizedBitmap = if (maxSize > CLIP_SIZE) {
        val resizeRate = maxSize.toDouble() / CLIP_SIZE.toDouble()
        Bitmap.createScaledBitmap(bitmap, (width/resizeRate).toInt(), (height/resizeRate).toInt(), false)
    } else bitmap

    this.setImageBitmap(resizedBitmap)
}

@BindingAdapter("app:layout_height")
fun ImageView.setLayoutHeight(height: Int) {
    val layoutParams: ViewGroup.LayoutParams = this.layoutParams
    layoutParams.height = height
    this.layoutParams = layoutParams
}
