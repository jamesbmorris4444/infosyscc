package com.sample.infosyscc.apiary_screen

import androidx.databinding.ObservableField
import com.sample.infosyscc.recyclerview.RecyclerViewItemViewModel
import com.sample.infosyscc.repository.storage.CardPair
import com.sample.infosyscc.utils.ApiaryCallbacks

class ApiaryItemTextViewModel(private val apiaryCallbacks: ApiaryCallbacks) : RecyclerViewItemViewModel<CardPair>() {

    val text = ObservableField("")
    val textSize = ObservableField(0.0f)
    val textColor = ObservableField("#ffffff")

    override fun setItem(item: CardPair) {
        text.set(item.card?.value ?: "NO CARD VALUE")
        textColor.set(item.card?.attributes?.text_color ?: "#000000")
        textSize.set((item.card?.attributes?.font?.size ?: 20).toFloat())
    }
}