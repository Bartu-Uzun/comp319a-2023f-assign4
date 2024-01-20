package com.example.phonebook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.phonebook.domain.model.Contact
import com.example.phonebook.presentation.add_contact.AddContactScreen
import com.example.phonebook.presentation.add_contact.AddContactUIEvent
import com.example.phonebook.presentation.add_contact.AddContactViewModel
import com.example.phonebook.presentation.home.HomeScreen
import com.example.phonebook.presentation.home.HomeUIEvent
import com.example.phonebook.presentation.home.HomeViewModel
import com.example.phonebook.ui.theme.PhonebookTheme
import com.example.phonebook.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhonebookTheme(dynamicColor = false) {
                // A surface container using the 'background' color from the theme

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.HomeScreen.route,
                ) {
                    composable(route = Screen.HomeScreen.route) {
                        val homeViewModel: HomeViewModel = hiltViewModel()
                        val homeUIState = homeViewModel.state.value

                        HomeScreen(
                            contactsList = homeUIState.contactList,
                            onAddContactClick = {
                                navController.navigate(Screen.AddContactScreen.route)
                            },
                            isFirstCall = homeUIState.isFirstCall,
                            onNavigatedBack = {
                                              homeViewModel.onEvent(HomeUIEvent.UpdateContent)
                            },
                            onDeleteContact = {contact: Contact ->
                                homeViewModel.onEvent(HomeUIEvent.DeleteContact(contact))
                            },
                            currentBackStackEntry = navController.currentBackStackEntryAsState().value,
                        )
                    }

                    composable(route = Screen.AddContactScreen.route) {

                        val addContactViewModel: AddContactViewModel = hiltViewModel()
                        val addContactUIState = addContactViewModel.state.value
                        val addContactResponseEvents = addContactViewModel.responseEvents

                        AddContactScreen(
                            onNavigateBack = {
                                navController.popBackStack()
                            },
                            onSubmitButtonClick = {
                                addContactViewModel.onEvent(AddContactUIEvent.SubmitNewContact)
                            },
                            onContactToAddNameChange = {contactName: String ->
                                addContactViewModel.onEvent(AddContactUIEvent.SetContactToAddName(name = contactName))

                            },
                            onContactToAddSurnameChange = {contactSurname: String ->
                                addContactViewModel.onEvent(AddContactUIEvent.SetContactToAddSurname(surname = contactSurname))

                            },
                            onContactToAddPhoneChange = {contactPhone: String ->
                                addContactViewModel.onEvent(AddContactUIEvent.SetContactToAddPhone(phone = contactPhone))

                            },
                            contactToAddName = addContactUIState.contactToAddName,
                            contactToAddSurname = addContactUIState.contactToAddSurname,
                            contactToAddPhone = addContactUIState.contactToAddPhone,
                            errorMessage = addContactUIState.errorMessage,
                            responseEvents = addContactResponseEvents,
                        )
                    }
                }
            }
        }
    }
}
