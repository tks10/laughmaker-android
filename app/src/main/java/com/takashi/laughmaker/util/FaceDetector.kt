package com.takashi.laughmaker.util

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

        fun isSmiling(firebaseImages: List<FirebaseVisionFace>, threshold: Float = 0.5f): Boolean {
            firebaseImages.forEach {
                if (it.smilingProbability >= threshold) return true
            }
            return false
        }
    }
}