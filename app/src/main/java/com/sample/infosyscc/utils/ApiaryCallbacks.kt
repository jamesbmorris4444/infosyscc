package com.sample.infosyscc.utils

import android.view.View
import com.sample.infosyscc.apiary_screen.ApiaryActivity
import com.sample.infosyscc.repository.Repository

interface ApiaryCallbacks {
    fun fetchApiaryActivity(): ApiaryActivity
    fun fetchApiaryRootView(): View
    fun fetchApiaryRepository(): Repository
}