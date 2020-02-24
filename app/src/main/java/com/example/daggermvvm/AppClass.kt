package com.example.daggermvvm

import android.app.Activity
import android.app.Application
import com.example.daggermvvm.dagger.AppComponent
import com.example.daggermvvm.dagger.ContextModule
import com.example.daggermvvm.dagger.DaggerAppComponent

class AppClass: Application() {
    lateinit var component: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()

    }

    companion object {
        fun getApplication(activity: Activity) = activity.application as AppClass
    }
}