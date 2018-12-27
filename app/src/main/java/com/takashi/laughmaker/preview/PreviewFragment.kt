package com.takashi.laughmaker.preview

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import com.takashi.laughmaker.R
import com.takashi.laughmaker.util.FaceDetector
import com.takashi.laughmaker.util.extractImages
import kotlinx.android.synthetic.main.fragment_preview.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class PreviewFragment : Fragment() {
    private val videoUri by lazy { PreviewFragmentArgs.fromBundle(arguments!!).videoUri }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_preview, container, false)
        view.videoView.setVideoURI(videoUri)

        view.videoView.setOnClickListener {
            val videoView = it as VideoView
            videoView.seekTo(0)
            videoView.start()
        }

        GlobalScope.launch {
            val frames = extractImages(context!!, videoUri)
            val smilingImages = mutableListOf<Bitmap>()

            frames.forEach { frame ->
                val task = FaceDetector.detect(frame)
                    .addOnSuccessListener {
                        if (FaceDetector.isSmiling(it)) {
                            smilingImages.add(frame)
                        }
                    }
                    .addOnFailureListener {
                        it.printStackTrace()
                    }
            }
        }

        return view
    }
}
