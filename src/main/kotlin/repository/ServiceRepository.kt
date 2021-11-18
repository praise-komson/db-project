package repository

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import db.DatabaseHelper
import entity.Service

object ServiceRepository {
    private val serviceQueries = DatabaseHelper.database.serviceQueries
    var services by mutableStateOf(emptyList<Service>())

    init {
        fetchServices()
    }

    fun fetchServices() {
        services = serviceQueries.getServices().executeAsList().map(::Service)
    }
}