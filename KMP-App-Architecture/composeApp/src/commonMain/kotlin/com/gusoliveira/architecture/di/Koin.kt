package com.gusoliveira.architecture.di

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
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import com.gusoliveira.architecture.screens.detail.DetailViewModel
import com.gusoliveira.architecture.screens.list.ListViewModel

val dataModule = module {
    single {
        val json = Json { ignoreUnknownKeys = true }
        HttpClient {
            install(ContentNegotiation) {
                // TODO Fix API so it serves application/json
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
}

val viewModelModule = module {
    factoryOf(::ListViewModel)
    factoryOf(::DetailViewModel)
}

fun initKoin() {
    startKoin {
        modules(
            dataModule,
            viewModelModule,
        )
    }
}
