// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.arkivanov.decompose.router.Router
import com.arkivanov.decompose.router.push
import com.arkivanov.decompose.router.replaceCurrent
import ui.components.BottomNav
import ui.navigation.Configuration
import ui.navigation.rememberRouter
import ui.screens.*
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
        Column {
            Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                Children(routerState = router.state) { screen ->
                    when (val config = screen.configuration) {
                        is Configuration.Profile ->
                            Profile()
                        is Configuration.MySessions ->
                            MySessions(
                                onNewSessionClick = { router.push(Configuration.BrowseServices) }
                            )
                        is Configuration.BrowseServices ->
                            BrowseServices(
                                onSelectService = { expertUsername, serviceName ->
                                    val newConfig = Configuration.NewSession(
                                        expertUsername, serviceName
                                    )
                                    router.push(newConfig)
                                }
                            )
                        is Configuration.NewSession ->
                            NewSession(config.expertUsername, config.serviceName)
                        is Configuration.SessionRequests ->
                            SessionRequests()
                    }.let {}
                }
            }
            BottomNavHost(router)
        }
    }
}

@Composable
fun BottomNavHost(
    router: Router<Configuration, Any>
) {
    when (val config = router.state.subscribeAsState().value.activeChild.configuration) {
        Configuration.MySessions, Configuration.Profile, Configuration.SessionRequests -> {
            BottomNav(
                currentConfig = config,
                onSetConfig = { router.replaceCurrent(it) }
            )
        }
        else -> {}
    }
}
