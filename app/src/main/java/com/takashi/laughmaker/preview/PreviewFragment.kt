package com.takashi.laughmaker.preview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.navigation.Navigation
import com.takashi.laughmaker.R
import kotlinx.android.synthetic.main.fragment_preview.view.*


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

        view.detectButton.setOnClickListener {
            val action = PreviewFragmentDirections.actionPreviewToProcess(videoUri)
            Navigation.findNavController(view!!).navigate(action)
        }

        return view
    }
}
