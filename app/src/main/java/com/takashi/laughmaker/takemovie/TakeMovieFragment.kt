package com.takashi.laughmaker.takemovie

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.takashi.laughmaker.R

class TakeMovieFragment : Fragment() {
    val REQUEST_VIDEO_CAPTURE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_takemovie, container, false)
        this.dispatchTakeVideoIntent()

        return view
    }

    private fun dispatchTakeVideoIntent() {
        if (activity == null) return
        Intent(MediaStore.ACTION_VIDEO_CAPTURE).also { takeVideoIntent ->
            takeVideoIntent.resolveActivity(activity!!.packageManager)?.also {
                startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent) {
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            val videoUri = intent.data ?: return
            val action = TakeMovieFragmentDirections.actionTakemocieToPreview(videoUri)
            Navigation.findNavController(view!!).navigate(action)
        }
    }
}
