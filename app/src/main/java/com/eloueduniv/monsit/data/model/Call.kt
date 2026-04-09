package com.eloueduniv.monsit.data.model

data class Call(
    val id: String,
    val startTime: Long,
    val duration: Long,
    val audioUrl : String,
    val transcript : String,
    val summary : String,
    val contactId : Int
)
