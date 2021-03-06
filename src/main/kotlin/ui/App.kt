// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.arkivanov.decompose.router.Router
import com.arkivanov.decompose.router.popWhile
import com.arkivanov.decompose.router.push
import com.arkivanov.decompose.router.replaceCurrent
import controller.UserController
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
        if (UserController.isLoggedIn) {
            Column {
                Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                    Children(routerState = router.state) { screen ->
                        when (val config = screen.configuration) {
                            is Configuration.Profile ->
                                Profile(
                                    onRouteChat = { router.push(Configuration.MyChats)}
                                )
                            is Configuration.MySessions ->
                                MySessions(
                                    onNewSessionClick = { router.push(Configuration.BrowseServices) }
                                )
                            is Configuration.MyRequests ->
                                MyRequests()
                            is Configuration.BrowseServices ->
                                BrowseServices(
                                    onSelectService = { service ->
                                        val newConfig = Configuration.NewSession(
                                            service
                                        )
                                        router.push(newConfig)
                                    }
                                )
                            is Configuration.NewSession ->
                                NewSession(config.service, onFinishNewSession = {
                                    router.popWhile { configuration -> configuration !is Configuration.MySessions }
                                })
                            is Configuration.SessionRequests ->
                                SessionRequests()
                            is Configuration.MyChats ->
                                MyChats(router)
                            is Configuration.ChatRoom ->
                                ChatRoom(config.chat._id)
                            is Configuration.CreateChat ->
                                key(UserController.username) { CreateChat(router) }
                        }.let {}
                    }
                }
                BottomNavHost(router)
            }
        } else {
            Login()
        }
    }
}

@Composable
fun BottomNavHost(
    router: Router<Configuration, Any>
) {
    when (val config = router.state.subscribeAsState().value.activeChild.configuration) {
        Configuration.MySessions, Configuration.MyRequests, Configuration.Profile, Configuration.SessionRequests -> {
            BottomNav(
                currentConfig = config,
                onSetConfig = { router.replaceCurrent(it) }
            )
        }
        else -> {}
    }
}
