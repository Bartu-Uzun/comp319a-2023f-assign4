package com.example.phonebook.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phonebook.domain.model.Contact
import com.example.phonebook.domain.repository.IContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val contactRepository: IContactRepository,
): ViewModel() {

    private val _state = mutableStateOf(HomeUIState())
    val state: State<HomeUIState> = _state

    init {
        setContent()
    }

    private fun setContent() {

        viewModelScope.launch (Dispatchers.IO) {
            val contactList = contactRepository.getAllContacts()

            withContext(Dispatchers.Main) {
                _state.value = state.value.copy(
                    contactList = contactList,
                    isFirstCall = false,
                )
            }
        }
    }

    fun onEvent(event: HomeUIEvent) {

        when (event) {
            is HomeUIEvent.DeleteContact -> {

                viewModelScope.launch{

                    contactRepository.deleteContact(event.contact)

                    val newContactList = state.value.contactList.toMutableList()

                    newContactList.remove(event.contact)

                    _state.value = state.value.copy(
                        contactList = newContactList.toList()
                    )
                }
            }

            HomeUIEvent.UpdateContent -> {
                setContent()
            }
        }
    }





}