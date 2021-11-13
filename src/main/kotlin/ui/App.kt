// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.router.push
import ui.navigation.Configuration
import ui.navigation.rememberRouter
import ui.screens.BrowseServices
import ui.screens.MySessions
import ui.screens.NewSession
import ui.theme.AppTheme

@Composable
@Preview
fun App() {
    val router =
        rememberRouter(
            initialConfiguration = { Configuration.MySessions },
            configurationClass = Configuration::class
        )

    AppTheme {
        Children(routerState = router.state) { screen ->
            when (val config = screen.configuration) {
                is Configuration.MySessions ->
                    MySessions(
                        onNewSessionClick = { router.push(Configuration.BrowseServices) }
                    )
                is Configuration.BrowseServices ->
                    BrowseServices(
                        onSelectService = { router.push(Configuration.NewSession) }
                    )
                is Configuration.NewSession ->
                    NewSession()
            }.let {}
        }
    }
}
