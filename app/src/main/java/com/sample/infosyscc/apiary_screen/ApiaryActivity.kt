package com.sample.infosyscc.apiary_screen

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.sample.infosyscc.R
import com.sample.infosyscc.databinding.ActivityApiaryBinding
import com.sample.infosyscc.repository.Repository
import com.sample.infosyscc.utils.ApiaryCallbacks
import timber.log.Timber

class ApiaryActivity : AppCompatActivity(), ApiaryCallbacks {

    private lateinit var repository: Repository
    lateinit var apiaryViewModel: ApiaryViewModel
    private lateinit var activityMainBinding: ActivityApiaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        repository = Repository()
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_apiary)
        activityMainBinding.lifecycleOwner = this
        apiaryViewModel = ViewModelProvider(this, ApiaryViewModelFactory(this)).get(ApiaryViewModel::class.java)
        activityMainBinding.apiaryViewModel = apiaryViewModel
        activityMainBinding.resultsListView.addItemDecoration(
            ApiaryViewModel.VerticalDividerItemDecoration(
                this,
                R.drawable.divider_drawable
            )
        )
    }

    override fun fetchApiaryActivity(): ApiaryActivity {
        return this
    }

    override fun fetchApiaryRootView(): View {
        return activityMainBinding.root
    }

    override fun fetchApiaryRepository(): Repository {
        return repository
    }
}