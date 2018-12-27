package com.takashi.laughmaker.util

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.graphics.Bitmap
import com.google.android.gms.tasks.Task
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.face.FirebaseVisionFace
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions


class FaceDetector {
    companion object {
        private val options = FirebaseVisionFaceDetectorOptions.Builder()
            .setModeType(FirebaseVisionFaceDetectorOptions.FAST_MODE)
            .setClassificationType(FirebaseVisionFaceDetectorOptions.ALL_CLASSIFICATIONS)
            .setMinFaceSize(0.2f)
            .setTrackingEnabled(false)
            .build()
        private val detector = FirebaseVision.getInstance().getVisionFaceDetector(options)

        fun detect(bitmap: Bitmap): Task<List<FirebaseVisionFace>> {
            val firebaseImage = FirebaseVisionImage.fromBitmap(bitmap)
            val task = detector.detectInImage(firebaseImage)

            return task
        }

        private fun isSmiling(firebaseImages: List<FirebaseVisionFace>, threshold: Float): Boolean {
            firebaseImages.forEach {
                if (it.smilingProbability >= threshold) return true
            }
            return false
        }

        fun executeSmileDetection(bitmapImages: List<Bitmap>, threshold: Float = 0.7f)
                : Pair<LiveData<List<Bitmap>>, LiveData<Double>> {
            val progressLiveData = MutableLiveData<Double>()
            val resultLiveData = MutableLiveData<List<Bitmap>>()
            val tmpResult = mutableListOf<Bitmap>()
            val frameCount = bitmapImages.size
            var detectedCount = 0

            bitmapImages.forEach { bitmap ->
                this.detect(bitmap)
                    .addOnSuccessListener {
                        if (this.isSmiling(it, threshold)) tmpResult.add(bitmap)
                    }
                    .addOnCompleteListener {
                        detectedCount++
                        progressLiveData.postValue(detectedCount.toDouble() / frameCount)
                        if (frameCount == detectedCount) {
                            resultLiveData.postValue(tmpResult)
                        }
                    }
            }

            return Pair(resultLiveData, progressLiveData)
        }
    }
}