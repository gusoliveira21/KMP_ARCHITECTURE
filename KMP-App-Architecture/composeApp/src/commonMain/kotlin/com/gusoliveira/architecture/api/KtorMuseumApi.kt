package com.gusoliveira.architecture.api

import data.api.MuseumApi
import domain.model.MuseumObject
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import kotlinx.serialization.json.Json
import kotlin.coroutines.cancellation.CancellationException

class KtorMuseumApi(private val client: HttpClient) : MuseumApi {
    companion object {
        private const val API_URL =
            "https://raw.githubusercontent.com/Kotlin/KMP-App-Template/main/list.json"
    }

    override suspend fun getData(): List<MuseumObject> {
        return try {
            val response = client.get(API_URL) {
                header(HttpHeaders.Accept, "application/json")
            }
            val jsonString = response.body<String>()

            val objects = Json.decodeFromString<List<MuseumObject>>(jsonString)

            objects
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            emptyList()
        }
    }
}