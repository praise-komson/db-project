// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.router.push
import ui.screens.BrowseServices
import ui.screens.Home
import ui.navigation.Configuration
import ui.navigation.rememberRouter

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
