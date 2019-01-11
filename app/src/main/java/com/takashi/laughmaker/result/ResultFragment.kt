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
import android.support.v7.widget.RecyclerView
import android.view.ViewTreeObserver

class ResultFragment : Fragment() {
    private val resultImages by lazy { ResultFragmentArgs.fromBundle(arguments!!).bitmapImages.map { ResultImage(it) } }
    private lateinit var resultListAdapter: ResultListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_result, container, false)
        resultListAdapter = ResultListAdapter(context!!, resultImages)
        view.resultList.adapter = resultListAdapter
        val layoutManager = GridLayoutManager(context!!, 3, LinearLayoutManager.VERTICAL, false)
        view.resultList.layoutManager = layoutManager

        val recyclerView = view!!.findViewById<RecyclerView>(R.id.resultList)
        recyclerView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val viewWidth = recyclerView.width
                resultListAdapter.imageViewHeight = viewWidth / 3
                resultListAdapter.notifyDataSetChanged()
                recyclerView.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })

        return view
    }
}

@Parcelize
class BitmapList(val list: List<Bitmap>) : ArrayList<Bitmap>(list), Parcelable