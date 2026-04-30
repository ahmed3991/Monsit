package com.eloueduniv.monsit.presentation.call.add

import java.util.Date

data class AddCallUiState(
    val contactName : String? = null,
    val callDate : Date = Date(),
    val callTime : Date = Date(),
    val duration : Long = 0,
    val audioUrl: String? = null,
    val note : String? = null
    )
