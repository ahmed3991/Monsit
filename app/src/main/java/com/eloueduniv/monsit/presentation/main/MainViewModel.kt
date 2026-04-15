package com.eloueduniv.monsit.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eloueduniv.monsit.domain.usecase.GetResentCallsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getResentCallsUseCase: GetResentCallsUseCase
) : ViewModel() {

    val _uiState = MutableStateFlow(UiState())

    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun loadResentCalls() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            getResentCallsUseCase().catch{
                _uiState.update { it.copy(error = it.error, isLoading = false) }
            }.collect { calls ->
                _uiState.update { it.copy(isLoading = false , calls = calls) }
            }
        }
    }

    init {
        loadResentCalls()
    }

}