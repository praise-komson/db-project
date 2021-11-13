package ui.navigation

import com.arkivanov.essenty.parcelable.Parcelable

sealed class Configuration : Parcelable {
    object MySessions : Configuration()
    object BrowseServices : Configuration()
    object NewSession : Configuration()
}
