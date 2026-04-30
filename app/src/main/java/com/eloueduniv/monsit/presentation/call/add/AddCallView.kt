package com.eloueduniv.monsit.presentation.call.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCallScreen(
    onBack: () -> Unit,
    viewModel: AddCallViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    // Date Picker State
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = uiState.callDate.time
    )

    // Time Picker State
    var showTimePicker by remember { mutableStateOf(false) }
    val timePickerState = rememberTimePickerState(
        initialHour = Calendar.getInstance().apply { time = uiState.callTime }.get(Calendar.HOUR_OF_DAY),
        initialMinute = Calendar.getInstance().apply { time = uiState.callTime }.get(Calendar.MINUTE)
    )

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let {
                        viewModel.onAction(AddCallUiAction.onCallDateChange(Date(it)))
                    }
                    showDatePicker = false
                }) { Text("OK") }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) { Text("Cancel") }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    if (showTimePicker) {
        AlertDialog(
            onDismissRequest = { showTimePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    val calendar = Calendar.getInstance().apply {
                        set(Calendar.HOUR_OF_DAY, timePickerState.hour)
                        set(Calendar.MINUTE, timePickerState.minute)
                    }
                    viewModel.onAction(AddCallUiAction.onCallTimeChange(calendar.time))
                    showTimePicker = false
                }) { Text("OK") }
            },
            dismissButton = {
                TextButton(onClick = { showTimePicker = false }) { Text("Cancel") }
            },
            text = {
                TimePicker(state = timePickerState)
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Add New Entry",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    TextButton(onClick = {
                        viewModel.onAction(AddCallUiAction.onAddCall)
                        onBack()
                    }) {
                        Text(
                            text = "Save",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF3F7FA)
                )
            )
        },
        containerColor = Color(0xFFF3F7FA)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Contact Name
            AddCallTextField(
                value = uiState.contactName ?: "",
                onValueChange = { viewModel.onAction(AddCallUiAction.onContactNameChange(it)) },
                label = "Contact Name",
                placeholder = "Contact Name"
            )

            // Call Date
            AddCallTextField(
                value = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(uiState.callDate),
                onValueChange = {},
                label = "Call Date",
                placeholder = "Call Date",
                trailingIcon = Icons.Default.DateRange,
                readOnly = true,
                onIconClick = { showDatePicker = true }
            )

            // Call Time
            AddCallTextField(
                value = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(uiState.callTime),
                onValueChange = {},
                label = "Call Time",
                placeholder = "Call Time",
                trailingIcon = Icons.Default.Notifications, // Closest to Clock icon in default icons
                readOnly = true,
                onIconClick = { showTimePicker = true }
            )

            // Duration
            AddCallTextField(
                value = if (uiState.duration == 0L) "" else uiState.duration.toString(),
                onValueChange = { 
                    val duration = it.toLongOrNull() ?: 0L
                    viewModel.onAction(AddCallUiAction.onDurationChange(duration)) 
                },
                label = "Duration (Min:Sec)",
                placeholder = "Duration (Min:Sec)"
            )

            // Audio URL
            AddCallTextField(
                value = uiState.audioUrl ?: "",
                onValueChange = { viewModel.onAction(AddCallUiAction.onAudioUrlChange(it)) },
                label = "Audio URL",
                placeholder = "Audio URL",
                trailingIcon = Icons.Default.Share // Representing link/audio icon
            )

            // Note
            AddCallTextField(
                value = uiState.note ?: "",
                onValueChange = { viewModel.onAction(AddCallUiAction.onNoteChange(it)) },
                label = "Add Note (optional)",
                placeholder = "Add Note (optional)",
                singleLine = false,
                modifier = Modifier.height(140.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Create Summary Button
            Button(
                onClick = { 
                    viewModel.onAction(AddCallUiAction.onAddCall)
                    onBack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD1E1EF),
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = "Create Summary",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun AddCallTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    trailingIcon: ImageVector? = null,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    onIconClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        label = { Text(label, style = MaterialTheme.typography.bodyMedium) },
        placeholder = { Text(placeholder) },
        trailingIcon = {
            if (trailingIcon != null) {
                IconButton(onClick = { onIconClick?.invoke() }) {
                    Icon(trailingIcon, contentDescription = null, tint = Color.Black.copy(alpha = 0.7f))
                }
            }
        },
        readOnly = readOnly,
        singleLine = singleLine,
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            focusedBorderColor = Color.Black.copy(alpha = 0.5f),
            unfocusedBorderColor = Color.Black.copy(alpha = 0.2f),
        )
    )
}