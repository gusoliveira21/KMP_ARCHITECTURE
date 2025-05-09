package com.gusoliveira.architecture

import android.app.Application
import timber.log.Timber

class ArchitectureApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        Timber.d("ArchitectureApp - onCreate")
    }
} 