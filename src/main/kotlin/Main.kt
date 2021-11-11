// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.router.pop
import com.arkivanov.decompose.router.push
import com.arkivanov.essenty.backpressed.BackPressedDispatcher
import navigation.Configuration
import navigation.LocalBackPressedDispatcher
import navigation.rememberRouter
import screens.BrowseServices
import screens.Home

@Composable
@Preview
fun App() {
    val router =
        rememberRouter(
            initialConfiguration = { Configuration.Home },
            configurationClass = Configuration::class
        )

    MaterialTheme {
        Children(routerState = router.state) { screen ->
            when (screen.configuration) {
                is Configuration.Home ->
                    Home(
                        onNewSessionClick = { router.push(Configuration.BrowseServices) }
                    )
                is Configuration.BrowseServices ->
                    BrowseServices()
            }.let {}
        }
    }
}

fun main() = application {
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
