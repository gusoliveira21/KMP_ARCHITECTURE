package com.gusoliveira.architecture.di

import com.gusoliveira.architecture.screens.detail.DetailViewModel
import data.api.MuseumApi
import data.api.KtorMuseumApi
import data.Storage.InMemoryMuseumStorage
import data.Storage.MuseumStorage
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
import com.gusoliveira.architecture.screens.list.ListViewModel
import data.repository.MuseumRepository

val dataModule = module {
    single {
        val json = Json { ignoreUnknownKeys = true }
        HttpClient { install(ContentNegotiation) {
            json(json, contentType = ContentType.Any) }
        }
    }
    single<Repository> { MuseumRepository(get(), get()).apply { initialize() }}
    single<MuseumApi> { KtorMuseumApi(get()) }
    single<MuseumStorage> { InMemoryMuseumStorage() }
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