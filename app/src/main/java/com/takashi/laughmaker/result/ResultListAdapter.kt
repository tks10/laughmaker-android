package com.takashi.laughmaker.result

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.takashi.laughmaker.databinding.ResultItemBinding
import com.takashi.laughmaker.result.Entity.*

class ResultListAdapter(val context: Context, private val resultImages: List<ResultImage>)
    : RecyclerView.Adapter<ResultListAdapter.ViewHolder>() {

    class ViewHolder(val binding: ResultItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ResultItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val resultImage = resultImages[position]
        // Binding
        holder.binding.resultImage = resultImage
    }

    override fun getItemCount() = resultImages.size
}
