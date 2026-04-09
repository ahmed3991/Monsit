package com.eloueduniv.monsit.data.repository

import com.eloueduniv.monsit.data.model.Call
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CallRepositoryImpl : CallRepository {

    private val _calls = mutableListOf(
        Call(
            id = "1",
            startTime = System.currentTimeMillis() - 1000 * 60 * 10, // 10 mins ago
            duration = 120,
            audioUrl = "https://example.com/audio1.mp3",
            transcript = "Hello, I am calling to confirm our meeting tomorrow at 10 AM.",
            summary = "Meeting confirmation for tomorrow at 10 AM.",
            contactId = 1
        ),
        Call(
            id = "2",
            startTime = System.currentTimeMillis() - 1000 * 60 * 60 * 2, // 2 hours ago
            duration = 45,
            audioUrl = "https://example.com/audio2.mp3",
            transcript = "Hey, don't forget to buy some milk on your way home.",
            summary = "Reminder to buy milk.",
            contactId = 2
        ),
        Call(
            id = "3",
            startTime = System.currentTimeMillis() - 1000 * 60 * 60 * 24, // 1 day ago
            duration = 300,
            audioUrl = "https://example.com/audio3.mp3",
            transcript = "We discussed the new project requirements and decided to move forward with the initial design.",
            summary = "Discussion on project requirements and design approval.",
            contactId = 3
        )
    )

    override fun getCall(id: String): Flow<Call> {
        return flowOf(_calls.first { it.id == id })
    }

    override fun getResentCalls(): Flow<List<Call>> {
        return flowOf(_calls.sortedByDescending { it.startTime })
    }

    override fun getCalls(): Flow<List<Call>> {
        return flowOf(_calls)
    }
}
