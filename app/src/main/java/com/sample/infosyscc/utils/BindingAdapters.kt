package com.sample.infosyscc.utils

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sample.infosyscc.recyclerview.RecyclerViewViewModel

@BindingAdapter("recyclerViewViewModel")
fun setRecyclerViewViewModel(recyclerView: RecyclerView, viewModel: RecyclerViewViewModel?) {
    viewModel?.setupRecyclerView(recyclerView)
}

@BindingAdapter("text_color")
fun setTextColor(view: TextView, color: String) {
    view.setTextColor(Color.parseColor(color))
}

@BindingAdapter("text_size")
fun setTextSize(view: TextView, size: Float) {
    view.textSize = size
}

@BindingAdapter("layout_width_dynamic")
fun setLayoutWidth(v: View, width: Int) {
    v.layoutParams.width = width
    v.requestLayout()
}

@BindingAdapter("layout_height_dynamic")
fun setLayoutHeight(v: View, height: Int) {
    v.layoutParams.height = height
    v.requestLayout()
}