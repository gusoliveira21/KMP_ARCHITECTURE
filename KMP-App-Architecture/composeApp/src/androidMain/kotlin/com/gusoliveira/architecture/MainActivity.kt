package com.gusoliveira.architecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.LaunchedEffect
import com.gusoliveira.architecture.di.NapierInitializer
import io.github.aakira.napier.Napier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        NapierInitializer.initialize()

        Napier.v("Verbose log")
        Napier.d("Debug log")
        Napier.i("Info log")
        Napier.w("Warning log")
        Napier.e("Error log", Throwable("Something went wrong"))

        setContent {
            LaunchedEffect(isSystemInDarkTheme()) {
                enableEdgeToEdge()
            }
            App()
        }
    }
}
