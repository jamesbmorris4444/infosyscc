package com.sample.infosyscc.repository.network

import com.sample.infosyscc.repository.storage.NewsResponsePage
import io.reactivex.Single
import retrofit2.http.GET

interface APIInterfaceNewsGET {
    @GET("home")
    fun getNewsFeed(): Single<NewsResponsePage>
}