package com.sample.infosyscc.apiary_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.sample.infosyscc.R
import com.sample.infosyscc.databinding.ItemApiaryImageTitleDescriptionBinding
import com.sample.infosyscc.databinding.ItemApiaryTextBinding
import com.sample.infosyscc.databinding.ItemApiaryTitleDescriptionBinding
import com.sample.infosyscc.recyclerview.RecyclerViewFilterAdapter
import com.sample.infosyscc.recyclerview.RecyclerViewItemViewModel
import com.sample.infosyscc.repository.storage.CardPair
import com.sample.infosyscc.utils.ApiaryCallbacks

class ApiaryAdapter(private val apiaryCallbacks: ApiaryCallbacks) : RecyclerViewFilterAdapter<CardPair, RecyclerViewItemViewModel<CardPair>>() {

    private var adapterFilter: AdapterFilter? = null

    override fun getFilter(): AdapterFilter {
        adapterFilter?.let {
            return it
        }
        return AdapterFilter()
    }

    enum class ViewTypes {
        TEXT,
        TITLE_DESCRIPTION,
        IMAGE_TITLE_DESCRIPTION
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            itemList[position].card_type == "text" -> ViewTypes.TEXT.ordinal
            itemList[position].card_type == "title_description" -> ViewTypes.TITLE_DESCRIPTION.ordinal
            itemList[position].card_type == "image_title_description" -> ViewTypes.IMAGE_TITLE_DESCRIPTION.ordinal
            else -> ViewTypes.TEXT.ordinal
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder<CardPair, RecyclerViewItemViewModel<CardPair>> {
        when (viewType) {
            ViewTypes.TEXT.ordinal -> {
                val itemApiaryTextBinding: ItemApiaryTextBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context), R.layout.item_apiary_text, parent, false)
                val apiaryItemTextViewModel = ApiaryItemTextViewModel(apiaryCallbacks)
                itemApiaryTextBinding.apiaryItemTextViewModel = apiaryItemTextViewModel
                return ApiaryItemTextViewHolder(itemApiaryTextBinding.root, apiaryItemTextViewModel as RecyclerViewItemViewModel<CardPair>, itemApiaryTextBinding)
            }
            ViewTypes.TITLE_DESCRIPTION.ordinal -> {
                val itemApiaryTitleDescriptionBinding: ItemApiaryTitleDescriptionBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_apiary_title_description, parent, false)
                val apiaryItemTitleDescriptionViewModel = ApiaryItemTitleDescriptionViewModel(apiaryCallbacks)
                itemApiaryTitleDescriptionBinding.apiaryItemTitleDescriptionViewModel = apiaryItemTitleDescriptionViewModel
                return ApiaryItemTitleDescriptionViewHolder(itemApiaryTitleDescriptionBinding.root, apiaryItemTitleDescriptionViewModel as RecyclerViewItemViewModel<CardPair>, itemApiaryTitleDescriptionBinding)

            }
            ViewTypes.IMAGE_TITLE_DESCRIPTION.ordinal -> {
                val itemApiaryImageTitleDescriptionBinding: ItemApiaryImageTitleDescriptionBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_apiary_image_title_description, parent, false)
                val apiaryItemImageTitleDescriptionViewModel = ApiaryItemImageTitleDescriptionViewModel(apiaryCallbacks)
                itemApiaryImageTitleDescriptionBinding.apiaryItemImageTitleDescriptionViewModel = apiaryItemImageTitleDescriptionViewModel
                return ApiaryItemImageTitleDescriptionViewHolder(itemApiaryImageTitleDescriptionBinding.root, apiaryItemImageTitleDescriptionViewModel as RecyclerViewItemViewModel<CardPair>, itemApiaryImageTitleDescriptionBinding)

            }
            else -> {
                val itemApiaryTextBinding: ItemApiaryTextBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_apiary_text, parent, false)
                val apiaryItemTextViewModel = ApiaryItemTextViewModel(apiaryCallbacks)
                itemApiaryTextBinding.apiaryItemTextViewModel = apiaryItemTextViewModel
                return ApiaryItemTextViewHolder(itemApiaryTextBinding.root, apiaryItemTextViewModel as RecyclerViewItemViewModel<CardPair>, itemApiaryTextBinding)
            }
        }
    }

    inner class ApiaryItemTextViewHolder internal constructor(itemView: View, viewModel: RecyclerViewItemViewModel<CardPair>, viewDataBinding: ItemApiaryTextBinding) :
        ItemViewHolder<CardPair, RecyclerViewItemViewModel<CardPair>> (itemView, viewModel, viewDataBinding)

    inner class ApiaryItemTitleDescriptionViewHolder internal constructor(itemView: View, viewModel: RecyclerViewItemViewModel<CardPair>, viewDataBinding: ItemApiaryTitleDescriptionBinding) :
        ItemViewHolder<CardPair, RecyclerViewItemViewModel<CardPair>> (itemView, viewModel, viewDataBinding)

    inner class ApiaryItemImageTitleDescriptionViewHolder internal constructor(itemView: View, viewModel: RecyclerViewItemViewModel<CardPair>, viewDataBinding: ItemApiaryImageTitleDescriptionBinding) :
        ItemViewHolder<CardPair, RecyclerViewItemViewModel<CardPair>> (itemView, viewModel, viewDataBinding)

    override fun onBindViewHolder(holder: ItemViewHolder<CardPair, RecyclerViewItemViewModel<CardPair>>, position: Int) {
        super.onBindViewHolder(holder, position)
        itemList[position].card?.image?.url?.let {
            if (itemList[position].card_type == "image_title_description") {
                Glide.with(apiaryCallbacks.fetchApiaryActivity()).load(it).into(holder.itemView.findViewById(R.id.image))
            }
        }
    }

    override fun itemFilterable(item: CardPair, constraint: String): Boolean {
        return true
    }

}