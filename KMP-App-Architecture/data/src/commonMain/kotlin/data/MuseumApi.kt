package data

import domain.model.MuseumObject
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.utils.io.CancellationException
import io.github.aakira.napier.Napier

interface MuseumApi {
    suspend fun getData(): List<MuseumObject>
}

class KtorMuseumApi(private val client: HttpClient) : MuseumApi {
    companion object {
        private const val API_URL =
            "https://raw.githubusercontent.com/Kotlin/KMP-App-Template/main/list.json"
    }

    override suspend fun getData(): List<MuseumObject> {
        return try {
            Napier.e("MuseumApi - getData - Iniciando requisição")
            val response: HttpResponse = client.get(API_URL)
            
            when (response.status) {
                HttpStatusCode.OK -> {
                    Napier.e("MuseumApi - getData - Sucesso")
                    response.body()
                }
                HttpStatusCode.TooManyRequests -> {
                    Napier.e("MuseumApi - getData - Too many requests - status 429")
                    emptyList()
                }
                else -> {
                    Napier.e("MuseumApi - getData - Error: ${response.status.value} - ${response.status.description}")
                    emptyList()
                }
            }
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Napier.e("MuseumApi - getData - Error fetching data: $e")
            emptyList()
        }
    }
}
