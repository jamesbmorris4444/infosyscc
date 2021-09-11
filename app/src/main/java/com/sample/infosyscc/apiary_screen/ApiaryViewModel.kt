package com.sample.infosyscc.apiary_screen

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.infosyscc.R
import com.sample.infosyscc.recyclerview.RecyclerViewViewModel
import com.sample.infosyscc.repository.Repository
import com.sample.infosyscc.repository.storage.CardPair
import com.sample.infosyscc.repository.storage.NewsResponsePage
import com.sample.infosyscc.utils.ApiaryCallbacks
import kotlinx.android.synthetic.main.activity_apiary.view.*


@Suppress("UNCHECKED_CAST")
class ApiaryViewModelFactory(private val apiaryCallbacks: ApiaryCallbacks) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ApiaryViewModel(apiaryCallbacks) as T
    }
}

@Suppress("UNCHECKED_CAST")
class ApiaryViewModel(private val apiaryCallbacks: ApiaryCallbacks) : RecyclerViewViewModel(apiaryCallbacks.fetchApiaryActivity().application) {

    private var repository: Repository = apiaryCallbacks.fetchApiaryRepository()

    override var adapter: ApiaryAdapter = ApiaryAdapter(apiaryCallbacks)
    override val itemDecorator: RecyclerView.ItemDecoration? = null
    private var adapterList: MutableList<CardPair> = mutableListOf()
    private lateinit var newsResponse: NewsResponsePage
    val listVisibility = ObservableField(View.VISIBLE)

    init {
        val progressBar: ProgressBar = apiaryCallbacks.fetchApiaryActivity().findViewById(R.id.apiary_progress_bar)
        progressBar.visibility = View.VISIBLE
        repository.getNewsFeed(this::showNews, this::showNewsError)
    }

    override fun setLayoutManager(): RecyclerView.LayoutManager {
        return object : LinearLayoutManager(getApplication<Application>().applicationContext) {
            override fun canScrollHorizontally(): Boolean {
                return false
            }

            override fun canScrollVertically(): Boolean {
                return true
            }
        }
    }

    @SuppressLint("LogNotTimber")
    private fun showNewsError(message: String?) {
        apiaryCallbacks.fetchApiaryRootView().apiary_progress_bar.visibility = View.GONE
        message?.let {
            Log.d("ERROR","Error msg=$it")
        }
    }

    private fun showNews(newsResponse: NewsResponsePage) {
        apiaryCallbacks.fetchApiaryRootView().apiary_progress_bar.visibility = View.GONE
        adapterList.clear()
        this.newsResponse = newsResponse
        for (element in newsResponse.page.cards) {
            adapterList.add(element)
        }
        adapter.addAll(adapterList)
        listVisibility.set(View.VISIBLE)
    }

    class VerticalDividerItemDecoration(context: Context, @DrawableRes dividerRes: Int) : RecyclerView.ItemDecoration() {
        private val divider: Drawable? = ContextCompat.getDrawable(context, dividerRes)
        override fun onDrawOver(c: Canvas, parent: RecyclerView) {
            val left = parent.paddingLeft
            val right = parent.width - parent.paddingRight
            val childCount = parent.childCount
            for (i in 0 until childCount) {
                val child = parent.getChildAt(i)
                val params = child.layoutParams as RecyclerView.LayoutParams
                val top = child.bottom + params.bottomMargin
                divider?.let { dividerDrawable ->
                    val bottom = top + divider.intrinsicHeight
                    dividerDrawable.setBounds(left, top, right, bottom)
                    dividerDrawable.draw(c)
                }
            }
        }
    }

}