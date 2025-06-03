package com.gusoliveira.architecture.di

import com.gusoliveira.architecture.controller.DetailController
import com.gusoliveira.architecture.controller.ListController
import com.gusoliveira.architecture.data.IMuseumApi
import com.gusoliveira.architecture.data.KtorIMuseumApi
import com.gusoliveira.architecture.data.InMemoryIMuseumStorage
import com.gusoliveira.architecture.data.MuseumRepository
import com.gusoliveira.architecture.data.IMuseumStorage
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.module

val dataModule = module {
    single {
        val json = Json { ignoreUnknownKeys = true }
        HttpClient {
            install(ContentNegotiation) {
                json(json, contentType = ContentType.Any)
            }
        }
    }

    single<IMuseumApi> { KtorIMuseumApi(get()) }
    single<IMuseumStorage> { InMemoryIMuseumStorage() }
    single {
        MuseumRepository(get(), get()).apply {
            initialize()
        }
    }

    factory { ListController(get()) }
    factory { DetailController(get()) }
}

fun initKoin() {
    startKoin {
        modules(
            dataModule,
        )
    }
}
