package data.api

import domain.model.MuseumObject
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlin.coroutines.cancellation.CancellationException

interface MuseumApi {
    suspend fun getData(): List<MuseumObject>
}