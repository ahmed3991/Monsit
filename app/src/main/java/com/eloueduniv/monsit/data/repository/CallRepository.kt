package com.eloueduniv.monsit.data.repository

import com.eloueduniv.monsit.data.model.Call
import kotlinx.coroutines.flow.Flow

interface CallRepository {

    fun getCalls(): Flow<List<Call>>

    fun getResentCalls(): Flow<List<Call>>

    fun getCall(id: String): Flow<Call>

}