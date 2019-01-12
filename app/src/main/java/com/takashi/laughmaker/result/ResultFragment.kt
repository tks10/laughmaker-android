package com.takashi.laughmaker.result

import android.graphics.Bitmap
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.takashi.laughmaker.R
import kotlinx.android.parcel.Parcelize
import com.takashi.laughmaker.result.Entity.ResultImage
import kotlinx.android.synthetic.main.fragment_result.view.*
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.GridLayoutManager

class ResultFragment : Fragment() {
    private val resultImages by lazy { ResultFragmentArgs.fromBundle(arguments!!).bitmapImages.map { ResultImage(it) } }
    private lateinit var resultListAdapter: ResultListAdapter
    private var gridSpanCount: Int = 3

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_result, container, false)
        resultListAdapter = ResultListAdapter(context!!, resultImages)
        view.resultList.adapter = resultListAdapter
        val layoutManager = GridLayoutManager(context!!, gridSpanCount, LinearLayoutManager.VERTICAL, false)
        view.resultList.layoutManager = layoutManager

        return view
    }
}

@Parcelize
class BitmapList(val list: List<Bitmap>) : ArrayList<Bitmap>(list), Parcelable