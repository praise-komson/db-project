package repository.utils

import androidx.compose.runtime.State

interface QueryState<T> : State<T> {
    fun refetch()
}
