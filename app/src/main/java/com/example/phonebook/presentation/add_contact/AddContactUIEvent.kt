package com.example.phonebook.presentation.add_contact

sealed class AddContactUIEvent {

    data class SetContactToAddPhone(val phone: String): AddContactUIEvent()
    data class SetContactToAddName(val name: String): AddContactUIEvent()
    data class SetContactToAddSurname(val surname: String): AddContactUIEvent()
    data class SetContactToAddProfilePicture(val photoSrc: String): AddContactUIEvent()

    object SubmitNewContact: AddContactUIEvent()
}
