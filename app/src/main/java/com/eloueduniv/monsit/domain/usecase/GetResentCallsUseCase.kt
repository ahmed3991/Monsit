package com.eloueduniv.monsit.domain.usecase

import com.eloueduniv.monsit.data.model.Call
import com.eloueduniv.monsit.data.repository.CallRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetResentCallsUseCase @Inject constructor(
    private val callRepository: CallRepository
){

    suspend operator fun invoke():Flow<List<Call>>{
        return callRepository.getResentCalls()
    }
}