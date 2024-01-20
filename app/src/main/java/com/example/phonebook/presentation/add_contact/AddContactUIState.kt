package com.example.phonebook.presentation.add_contact

data class AddContactUIState(

    val contactToAddName: String = "",
    val contactToAddSurname: String = "",
    val contactToAddPhone: String = "",
    val contactToAddProfilePicture: String = "",
    val errorMessage: String = "",
)
