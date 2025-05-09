package domain.usercase.get

import domain.usercase.base.BaseUseCase
import domain.usercase.base.DataResult

class GetObjects(): BaseUseCase<Unit, Unit>(){
    override suspend fun doWork(value: Unit): DataResult<Unit> {
        TODO("Not yet implemented")
    }
}