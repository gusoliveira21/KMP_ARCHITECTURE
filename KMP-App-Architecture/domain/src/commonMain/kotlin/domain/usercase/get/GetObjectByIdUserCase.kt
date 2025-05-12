package domain.usercase.get

import domain.model.MuseumObject
import domain.network.NetworkResult
import domain.repository.Repository
import domain.usercase.base.BaseUseCase
import domain.usercase.base.DataResult
import kotlinx.coroutines.flow.Flow

class GetObjectByIdUserCase(private val repository: Repository) : BaseUseCase<Int, Flow<MuseumObject?>>() {
    override suspend fun doWork(value: Int): DataResult<Flow<MuseumObject?>> {
        return try {
            when (val response = repository.getObjectById(value)) {
                is NetworkResult.Success -> {
                    DataResult.Success(response.data)
                }
                is NetworkResult.Error -> {
                    DataResult.Failure(
                        Throwable(
                            response.body?.error ?: "Unknown error on GetObjectByIdUserCase"
                        )
                    )
                }
                is NetworkResult.Exception -> {
                    DataResult.Failure(Throwable(response.e))
                }
            }
        } catch (e: Exception) {
            DataResult.Failure(e)
        }
    }
}