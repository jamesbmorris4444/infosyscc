package com.sample.infosyscc.apiary_screen

import androidx.databinding.ObservableField
import com.sample.infosyscc.recyclerview.RecyclerViewItemViewModel
import com.sample.infosyscc.repository.storage.CardPair
import com.sample.infosyscc.utils.ApiaryCallbacks

class ApiaryItemImageTitleDescriptionViewModel(private val apiaryCallbacks: ApiaryCallbacks) : RecyclerViewItemViewModel<CardPair>() {

    val titleText = ObservableField("")
    val titleTextSize = ObservableField(0.0f)
    val titleTextColor = ObservableField("#ffffff")
    val descriptionText = ObservableField("")
    val descriptionTextSize = ObservableField(0.0f)
    val descriptionTextColor = ObservableField("#ffffff")
    val imageWidth = ObservableField(0)
    val imageHeight = ObservableField(0)

    override fun setItem(item: CardPair) {
        titleText.set(item.card?.title?.value ?: "NO CARD_TITLE")
        titleTextSize.set((item.card?.title?.attributes?.font?.size ?: 20).toFloat())
        titleTextColor.set(item.card?.title?.attributes?.text_color ?: "#000000")
        descriptionText.set(item.card?.description?.value ?: "NO CARD_DESCRIPTION")
        descriptionTextSize.set((item.card?.description?.attributes?.font?.size ?: 20).toFloat())
        descriptionTextColor.set(item.card?.description?.attributes?.text_color ?: "#000000")
        imageWidth.set(item.card?.image?.size?.width ?: 500)
        imageHeight.set(item.card?.image?.size?.height ?: 500)
    }
}