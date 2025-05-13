package com.gusoliveira.architecture.data

interface IMuseumApi {
    suspend fun getData(): List<MuseumObject>
}