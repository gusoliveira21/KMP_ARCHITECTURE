package domain.usercase.get

import domain.model.MuseumObject
import domain.network.NetworkResult
import domain.repository.Repository
import domain.usercase.base.BaseUseCase
import domain.usercase.base.DataResult
import kotlinx.coroutines.flow.Flow

class GetData(private val repository: Repository) : BaseUseCase<Unit, Flow<List<MuseumObject>>>() {

    override suspend fun doWork(value: Unit): DataResult<Flow<List<MuseumObject>>> {

        return try {
            // Primeiro faz o refresh dos dados
            when (val response = repository.getObjects()) {
                is NetworkResult.Success -> {
                    DataResult.Success(repository.getObjectsFlow())
                }
                is NetworkResult.Error -> {
                    DataResult.Failure(
                        Throwable(
                            response.body?.error ?: "Unknown error on GetDataUserCase"
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