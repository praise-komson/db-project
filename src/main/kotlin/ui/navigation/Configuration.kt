package ui.navigation

import com.arkivanov.essenty.parcelable.Parcelable

sealed class Configuration : Parcelable {
    object MySessions : Configuration()
    object BrowseServices : Configuration()
    data class NewSession(val expertUsername: String, val serviceName: String) : Configuration()

    object SessionRequests : Configuration()
}
