package com.sample.infosyscc.repository

import com.sample.infosyscc.repository.network.APIClient
import com.sample.infosyscc.repository.network.APIInterfaceNewsGET
import com.sample.infosyscc.repository.storage.NewsResponsePage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class Repository {

    private val apiInterfaceNewsGet: APIInterfaceNewsGET = APIClient.newsGetClient
    private lateinit var disposable: Disposable

    fun getNewsFeed(showNews: (newsResponse: NewsResponsePage) -> Unit, showNewsError: (error: String?) -> Unit) {
        disposable = apiInterfaceNewsGet.getNewsFeed()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .timeout(15L, TimeUnit.SECONDS)
            .subscribe({ newsResponse ->
                disposable.dispose()
                showNews(newsResponse)
            },
            { throwable ->
                disposable?.dispose()
                showNewsError(throwable.message)
            })
    }

}