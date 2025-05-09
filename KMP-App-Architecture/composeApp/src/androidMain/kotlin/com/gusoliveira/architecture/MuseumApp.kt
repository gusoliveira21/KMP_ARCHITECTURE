package com.gusoliveira.architecture

import android.app.Application
import com.gusoliveira.architecture.di.initKoin
import org.koin.android.BuildConfig

class MuseumApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}
