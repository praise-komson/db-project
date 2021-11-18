package ui.navigation

import com.arkivanov.essenty.parcelable.Parcelable
import entity.Service

sealed class Configuration : Parcelable {
    object Profile : Configuration()
    object MySessions : Configuration()
    object BrowseServices : Configuration()
    data class NewSession(val service: Service) : Configuration()

    object SessionRequests : Configuration()
    object MyRequests : Configuration()

}
