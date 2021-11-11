package navigation

import com.arkivanov.essenty.parcelable.Parcelable

sealed class Configuration : Parcelable {
    object Home : Configuration()
    object BrowseServices : Configuration()
}
