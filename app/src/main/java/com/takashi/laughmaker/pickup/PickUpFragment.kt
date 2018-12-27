package com.takashi.laughmaker.pickup

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.takashi.laughmaker.R
import kotlinx.android.synthetic.main.fragment_pickup.view.*


class PickUpFragment : Fragment() {
    val RESULT_PICK_VIDEO_FILE: Int = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_pickup, container, false)
        view.addVideoImageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)

            // Filter to only show results that can be "opened", such as a
            // file (as opposed to a list of contacts or timezones)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "video/*"

            startActivityForResult(intent, RESULT_PICK_VIDEO_FILE)
        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent) {
        if (requestCode == RESULT_PICK_VIDEO_FILE && resultCode == Activity.RESULT_OK) {
            val videoUri = intent.data ?: return
            val action = PickUpFragmentDirections.actionTakemovieToPreview(videoUri)
            Navigation.findNavController(view!!).navigate(action)
        }
    }
}
