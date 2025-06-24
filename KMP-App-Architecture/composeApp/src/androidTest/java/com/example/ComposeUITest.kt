package com.example

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComposeUITest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testComposeUI() {
        // Teste básico para verificar elementos Compose
        // Você pode adicionar mais verificações específicas aqui
        composeTestRule.onNodeWithText("Hello World").assertIsDisplayed()
    }
} 