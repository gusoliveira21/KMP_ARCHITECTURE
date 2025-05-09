package com.gusoliveira.architecture.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.aakira.napier.Napier

@Composable
fun EmptyScreenContent(modifier: Modifier = Modifier) {
    Napier.e("EmptyScreenContent - Composable")
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("No data available")
    }
}
