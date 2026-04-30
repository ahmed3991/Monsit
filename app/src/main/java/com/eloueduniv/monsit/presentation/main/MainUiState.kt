package com.eloueduniv.monsit.presentation.main

import com.eloueduniv.monsit.data.model.Call

data class MainUiState(
    val isLoading: Boolean = false,
    val calls: List<Call> = emptyList(),
    val error: String? = null
)
