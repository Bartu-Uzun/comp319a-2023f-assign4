package com.example.phonebook.presentation.add_contact

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phonebook.domain.model.Contact
import com.example.phonebook.domain.model.InvalidContactException
import com.example.phonebook.domain.repository.IContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddContactViewModel @Inject constructor(
    private val contactRepository: IContactRepository,
): ViewModel()  {

    private val _state = mutableStateOf(AddContactUIState())
    val state: State<AddContactUIState> = _state

    private val responseEventChannel = Channel<ResponseEvent>() // channel with only one observer
    val responseEvents = responseEventChannel.receiveAsFlow() // receiveAsFlow returns an immutable flow, will be observed by the ui


    fun onEvent(event: AddContactUIEvent) {

        when (event) {
            is AddContactUIEvent.SetContactToAddName -> {
                _state.value = state.value.copy(
                    contactToAddName = event.name,
                )
            }
            is AddContactUIEvent.SetContactToAddPhone -> {
                _state.value = state.value.copy(
                    contactToAddPhone = event.phone,
                )
            }
            is AddContactUIEvent.SetContactToAddProfilePicture -> {
                _state.value = state.value.copy(
                    contactToAddProfilePicture = event.photoSrc,
                )
            }
            is AddContactUIEvent.SetContactToAddSurname -> {
                _state.value = state.value.copy(
                    contactToAddSurname = event.surname,
                )
            }
            AddContactUIEvent.SubmitNewContact -> {
                viewModelScope.launch{


                    try {

                        val photoSrc = "https://api.multiavatar.com/${state.value.contactToAddName}.png"
                        contactRepository.insertContact(
                            Contact(
                                phone = state.value.contactToAddPhone,
                                name = state.value.contactToAddName.lowercase(),
                                surname = state.value.contactToAddSurname.lowercase(),
                                profilePicture = photoSrc,
                            )
                        )

                        _state.value = state.value.copy(
                            errorMessage = "",
                            contactToAddPhone = "",
                            contactToAddName = "",
                            contactToAddSurname = "",
                            contactToAddProfilePicture = ""
                        )

                        responseEventChannel.send(ResponseEvent.NavigateBack) // navigate to home

                    } catch (e: InvalidContactException) {

                        _state.value = state.value.copy(
                            errorMessage = e.message ?: "Error when adding contact"
                        )

                    }
                }
            }
        }
    }

    /* one time events that ViewModel sends, and the UI reads
    *       we do not want to hold onto these events unlike states that are persistent
    *       */
    sealed class ResponseEvent {

        object NavigateBack: ResponseEvent()

    }
}