package com.example.phonebook.presentation.home

import com.example.phonebook.domain.model.Contact

sealed class HomeUIEvent {
    data class DeleteContact(val contact: Contact): HomeUIEvent()
    object UpdateContent: HomeUIEvent()



}
