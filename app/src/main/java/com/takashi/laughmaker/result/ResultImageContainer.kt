package com.takashi.laughmaker.result

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

class ResultImageContainer(
    context: Context,
    attrs: AttributeSet?
) : ImageView(context, attrs) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measuredWidth, measuredWidth)
    }
}