// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.essenty.backpressed.BackPressedDispatcher
import db.DatabaseHelper
import ui.navigation.LocalBackPressedDispatcher

fun main() = application {
    DatabaseHelper
    Window(
        onCloseRequest = ::exitApplication,
        state = rememberWindowState(
            size = DpSize(375.dp, 800.dp)
        ),
        title = "Doji",
        resizable = false
    ) {
        val backPressedDispatcher = remember { BackPressedDispatcher() }
        CompositionLocalProvider(LocalBackPressedDispatcher provides backPressedDispatcher) {
            App()
        }
    }
}
