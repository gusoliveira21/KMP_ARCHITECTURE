package com.gusoliveira.architecture

import androidx.compose.ui.window.ComposeUIViewController
import io.github.aakira.napier.Napier

fun MainViewController() = ComposeUIViewController { 
    Napier.e("MainViewController - viewDidLoad")
    App() 
}
