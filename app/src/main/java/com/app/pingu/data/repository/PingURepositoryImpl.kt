package com.app.pingu.data.repository

import com.app.pingu.data.mapper.PingUMapper
import com.app.pingu.domain.repository.PingURepository
import javax.inject.Inject

class PingURepositoryImpl @Inject constructor(
    private val mapper: PingUMapper
) : PingURepository {

}