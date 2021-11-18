package repository.utils

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf

fun <T> makeQueryState(calculation: () -> T): QueryState<T> {
    val refetchCount = mutableStateOf(0)
    val state = derivedStateOf {
        refetchCount.value
        calculation()
    }

    return object : QueryState<T> {
        override val value by state

        override fun refetch() {
            refetchCount.value++
        }
    }
}
