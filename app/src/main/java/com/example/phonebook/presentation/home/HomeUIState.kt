package com.example.phonebook.presentation.home

import com.example.phonebook.domain.model.Contact
import com.example.phonebook.domain.util.ScreenState

data class HomeUIState(
    val screenState: ScreenState = ScreenState.Loading,
    val contactList: List<Contact> = listOf(),
    val isFirstCall: Boolean = true,
    )
