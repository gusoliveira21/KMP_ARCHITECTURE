package domain.usercase.get

import domain.model.MuseumObject
import domain.network.NetworkResult
import domain.repository.Repository
import domain.usercase.base.BaseUseCase
import domain.usercase.base.DataResult
import io.github.aakira.napier.Napier

class GetData(private val repository: Repository) : BaseUseCase<Unit, List<MuseumObject>>() {

    override suspend fun doWork(value: Unit): DataResult<List<MuseumObject>> {
        Napier.e("GetData - doWork")

        return try {
            when (val response = repository.getObjects()) {
                is NetworkResult.Success -> {
                    Napier.e("GetData - Success \n ${response.data}")
                    DataResult.Success(response.data)
                }
                is NetworkResult.Error -> {
                    Napier.e("GetData - Error: ${response.body?.error}")
                    DataResult.Failure(
                        Throwable(
                            response.body?.error ?: "Unknown error on GetDataUserCase"
                        )
                    )
                }
                is NetworkResult.Exception -> {
                    Napier.e("GetData - Exception: ${response.e}")
                    DataResult.Failure(Throwable(response.e))
                }
            }
        } catch (e: Exception) {
            Napier.e("GetData - Exception: $e")
            DataResult.Failure(e)
        }
    }
}