package com.gusoliveira.architecture.di

import com.gusoliveira.architecture.screens.detail.DetailViewModel
import data.api.MuseumApi
import com.gusoliveira.architecture.KtorMuseumApi
import data.Storage.InMemoryMuseumStorage
import data.Storage.MuseumStorage
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.module
import domain.repository.Repository
import domain.usercase.get.GetData
import org.koin.core.component.KoinComponent
import com.gusoliveira.architecture.screens.list.ListViewModel
import data.repository.MuseumRepository

val dataModule = module {
    single {
        val json = Json { 
            ignoreUnknownKeys = true
            isLenient = true
            coerceInputValues = true
        }
        HttpClient {
            install(ContentNegotiation) {
                json(json)
            }
        }
    }
    single<MuseumStorage> { InMemoryMuseumStorage() }
    single<MuseumApi> { KtorMuseumApi(get()) }
    single<Repository> { MuseumRepository(get(), get()).apply { initialize() } }
    single { GetData(get()) }
}

val viewModelModule = module {
    factory { ListViewModel(get()) }
    factory { DetailViewModel(get()) }
}

class AppInitializer : KoinComponent {
    fun initKoin() {
        startKoin() {
            modules(
                dataModule,
                viewModelModule,
            )
        }
    }
}

fun initKoin() = AppInitializer().initKoin()