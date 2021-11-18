package controller

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import repository.ServiceRepository

object ServiceController {
    val services by derivedStateOf { ServiceRepository.services }
}