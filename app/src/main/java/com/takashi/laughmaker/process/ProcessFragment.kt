package com.takashi.laughmaker.process

import android.arch.lifecycle.Observer
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.takashi.laughmaker.R
import com.takashi.laughmaker.result.BitmapList
import com.takashi.laughmaker.util.FaceDetector
import com.takashi.laughmaker.util.extractImages
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ProcessFragment : Fragment() {
    private val videoUri by lazy { ProcessFragmentArgs.fromBundle(arguments!!).videoUri }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_process, container, false)
        processImage()

        return view
    }

    private fun processImage() {
        GlobalScope.launch {
            val frames = extractImages(context!!, videoUri)
            val (resultLiveData, progressLiveData) = FaceDetector.executeSmileDetection(frames)

            progressLiveData.observe(this@ProcessFragment, Observer<Double> {
                Log.e("Progress", it.toString())
            })
            resultLiveData.observe(this@ProcessFragment, Observer<List<Bitmap>> {
                Log.e("Smile", "Done!")
                val action =
                    ProcessFragmentDirections.actionProcessToResult(BitmapList(it!!))
                Navigation.findNavController(view!!).navigate(action)

            })
        }
    }
}

