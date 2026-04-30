package com.eloueduniv.monsit.domain.usecase

import com.eloueduniv.monsit.data.model.Call
import com.eloueduniv.monsit.data.repository.CallRepository
import javax.inject.Inject

class AddCallUseCase @Inject constructor( private val callRepository: CallRepository) {
     operator fun invoke(call: Call) {
        callRepository.addCall(call)
    }
}