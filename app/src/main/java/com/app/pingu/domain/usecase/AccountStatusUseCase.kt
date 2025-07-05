package com.app.pingu.domain.usecase

import com.app.pingu.domain.repository.PingURepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class AccountStatusUseCase @Inject constructor(
    private val repository: PingURepository,
    private val dispatcher: CoroutineDispatcher
) {
    /* operator fun invoke(token: String): Flow<RestResult<*>> {
        return flow {
            val response = repository.accountStatus(token)
            emit(response)
        }.buildFlow(dispatcher)
    } */
}