package com.eloueduniv.monsit.data.repository

import com.eloueduniv.monsit.data.model.Call
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class CallRepositoryImpl : CallRepository {

    private val _calls = mutableListOf(
        Call(
            id = "1",
            contactName = "Amelie Davies",
            startTime = System.currentTimeMillis() - 1000 * 60 * 10, // 10 mins ago
            duration = 120,
            audioUrl = "https://example.com/audio1.mp3",
            transcript = "Hello, I am calling to confirm our meeting tomorrow at 10 AM.",
            summary = "Discussed project timelines and final key deliverables...",
            contactId = 1,
            note = null
        ),
        Call(
            id = "2",
            contactName = "Amelie Davies",
            startTime = System.currentTimeMillis() - 1000 * 60 * 60 * 2, // 2 hours ago
            duration = 45,
            audioUrl = "https://example.com/audio2.mp3",
            transcript = "Hey, don't forget to buy some milk on your way home.",
            summary = "Discussed project timelines and final key deliverables...",
            contactId = 2,
            note = null
        ),
        Call(
            id = "3",
            contactName = "Amelie Davies",
            startTime = System.currentTimeMillis() - 1000 * 60 * 60 * 24, // 1 day ago
            duration = 300,
            audioUrl = "https://example.com/audio3.mp3",
            transcript = "We discussed the new project requirements and decided to move forward with the initial design.",
            summary = "Discussed project timelines and final key deliverables...",
            contactId = 3,
            note = null
        )
    )

    private val callsFlow = MutableSharedFlow<List<Call>>(replay = 1).apply {
        tryEmit(_calls.toList())
    }


    override fun getCall(id: String): Call? {
        return _calls.first { it.id == id }
    }

    override fun getResentCalls(): Flow<List<Call>> =flow {
        delay(2000) // Simulate delay
        emitAll(callsFlow)
    }

    override fun getCalls(): Flow<List<Call>> =flow {
        delay(2000) // Simulate delay
        emitAll(callsFlow)
    }

    override fun addCall(call: Call) {
        _calls.add(call)
        callsFlow.tryEmit(_calls.toList())
    }
}
