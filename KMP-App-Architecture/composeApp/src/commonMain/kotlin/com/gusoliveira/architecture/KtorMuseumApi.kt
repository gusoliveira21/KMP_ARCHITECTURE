package com.gusoliveira.architecture

import data.api.MuseumApi
import domain.model.MuseumObject
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.github.aakira.napier.Napier
import io.ktor.http.ContentDisposition.Companion.File
import kotlinx.io.files.SystemFileSystem
import kotlinx.serialization.json.Json
import okio.FileSystem
import kotlin.coroutines.cancellation.CancellationException

class KtorMuseumApi(private val client: HttpClient) : MuseumApi {
    companion object {
        private const val API_URL =
            "https://raw.githubusercontent.com/Kotlin/KMP-App-Template/main/list.json"
    }

    override suspend fun getData(): List<MuseumObject> {
        return try {
            Napier.e("KtorMuseumApi - getData - Iniciando requisição")
            val response = client.get(API_URL) {
                header(HttpHeaders.Accept, "application/json")
            }
            val jsonString = response.body<String>()
            Napier.e("KtorMuseumApi - getData - Sucesso-jsonString: ${jsonString}")

            val objects = Json.decodeFromString<List<MuseumObject>>(jsonString)
            Napier.e("KtorMuseumApi - getData - Sucesso: ${objects.size} objetos")

            objects
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Napier.e("KtorMuseumApi - getData - Erro: ${e.message}")
            e.printStackTrace()
            emptyList()
        }
    }
}