package com.gusoliveira.architecture.di

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

object NapierInitializer {
    fun initialize() {
        Napier.base(DebugAntilog())
    }
} 