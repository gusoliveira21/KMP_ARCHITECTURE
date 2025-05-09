package com.gusoliveira.architecture.di

import com.gusoliveira.architecture.screens.detail.DetailViewModel
import data.MuseumApi
import data.KtorMuseumApi
import data.InMemoryMuseumStorage
import data.repository.MuseumRepository
import data.MuseumStorage
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.module
import domain.repository.Repository
import domain.usercase.get.GetData
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope
import com.gusoliveira.architecture.screens.list.ListViewModel

val dataModule = module {
    single {
        val json = Json { ignoreUnknownKeys = true }
        HttpClient {
            install(ContentNegotiation) {
                json(json, contentType = ContentType.Any)
            }
        }
    }

    single<MuseumApi> { KtorMuseumApi(get()) }
    single<MuseumStorage> { InMemoryMuseumStorage() }
    single<Repository> { MuseumRepository(get(), get()) }
    single { GetData(get()) }
}

val viewModelModule = module {
    factory { ListViewModel(get()) }
    factory { DetailViewModel(get()) }
}

class AppInitializer : KoinComponent {
    private val repository: Repository by inject()

    fun initKoin() {
        startKoin {
            modules(
                dataModule,
                viewModelModule,
            )
        }

        CoroutineScope(Dispatchers.Main).launch {
            repository.initialize()
        }
    }
}

fun initKoin() = AppInitializer().initKoin()