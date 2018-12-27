package com.takashi.laughmaker.result

import android.graphics.Bitmap

interface Entity {
    data class ResultImage(
        val bitmapImage: Bitmap
    )
}
