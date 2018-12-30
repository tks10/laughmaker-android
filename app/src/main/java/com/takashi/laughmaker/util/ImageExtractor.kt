package com.takashi.laughmaker.util

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.util.Log

fun extractImages(context: Context, uri: Uri, spanMs: Int = 200): List<Bitmap> {
    val mmr = MediaMetadataRetriever()
    mmr.setDataSource(context, uri)

    val durationMs = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION).toInt()
    val bitmapList = mutableListOf<Bitmap>()

    // Extract
    Log.d("Extractor", "The duration of the video is $durationMs [ms].")
    for (ms in 0..durationMs step spanMs) {
        Log.d("Extractor", ms.toString())
        bitmapList.add(mmr.getFrameAtTime(ms * 1000L))
    }

    return bitmapList
}
