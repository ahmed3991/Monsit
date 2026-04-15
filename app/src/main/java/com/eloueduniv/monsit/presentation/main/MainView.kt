package com.eloueduniv.monsit.presentation.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.isEmpty
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()){

    val uiState by viewModel.uiState.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when {
                uiState.isLoading -> {
                    // 1. Show waiting circle when loading
                    CircularProgressIndicator()
                }
                uiState.error != null -> {
                    // 2. Show error message if something went wrong
                    Text(
                        text = uiState.error!!,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                else -> {
                    // 3. Show recent calls
                    if (uiState.calls.isEmpty()) {
                        Text(text = "No recent calls found")
                    } else {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            items(uiState.calls) { call ->
                                ListItem(
                                    headlineContent = { Text(text = "Contact"+call.contactId) },
                                    supportingContent = { Text(text = ""+call.startTime) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}