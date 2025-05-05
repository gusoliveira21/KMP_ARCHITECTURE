package com.gusoliveira.architecture

import android.app.Application
import com.gusoliveira.architecture.di.initKoin

class MuseumApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}
