package com.example.phonebook.util

sealed class Screen(val route: String) {

    object HomeScreen: Screen(route = "home_screen")
    object AddContactScreen: Screen(route = "add_contact_screen")

}