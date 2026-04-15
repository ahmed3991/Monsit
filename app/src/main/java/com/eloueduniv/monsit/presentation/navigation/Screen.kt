package com.eloueduniv.monsit.presentation.navigation

sealed class Screen (val route: String) {

    object Main: Screen("main")

    object Contacts: Screen("contacts")

    object Search: Screen("search")

    object AddCall: Screen("add_call")

    object CallDetail: Screen("call_detail/callId"){
        fun createRoute(callId: String) = "call_detail/$callId"
    }

    object ContactDetail: Screen("contact_detail/contactId"){
        fun createRoute(contactId: String) = "contact_detail/$contactId"
    }

    object MainScreen: Screen("main_screen")

}