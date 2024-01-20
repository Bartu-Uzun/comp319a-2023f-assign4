package com.example.phonebook.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import com.example.phonebook.R
import com.example.phonebook.domain.model.Contact
import com.example.phonebook.presentation.home.components.ContactsLazyList
import com.example.phonebook.ui.theme.PhonebookTheme
import com.example.phonebook.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    contactsList: List<Contact>,
    onAddContactClick: () -> Unit,
    onNavigatedBack: () -> Unit,
    onDeleteContact: (Contact) -> Unit,
    currentBackStackEntry: NavBackStackEntry?,
    isFirstCall: Boolean,
) {

    /* this LaunchedEffect will be called everytime backStackEntry changes, even if this screen is not active */
    LaunchedEffect(currentBackStackEntry) {

        if (currentBackStackEntry != null) {

            val destination = currentBackStackEntry.destination.route // check if the current active screen is this screen

            if (!isFirstCall && destination == Screen.HomeScreen.route) {
                onNavigatedBack()
            }


        }

    }

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Column (
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ){
                        Text(
                            text = stringResource(id = R.string.phonebook),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                actions = {},
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = onAddContactClick
                    ) {

                        Icon(
                            modifier = Modifier
                                .size(40.dp),
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(id = R.string.add_contact)
                        )
                        
                    }
                }
            )
        }
    ){

        Surface (
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.background
                )
                .padding(it)
        ) {

            val grouped = contactsList.groupBy {
                it.name[0]
            }.toSortedMap()

            ContactsLazyList(
                grouped = grouped,
                onDeleteContact = onDeleteContact
                )

        }

    }
}

/*
@Preview(showBackground = true)
@Composable
fun HomePrev() {
    PhonebookTheme (dynamicColor = false) {
        HomeScreen(
            contactsList = listOf(
                Contact(
                    name = "Bartu",
                    surname = "Uzun",
                    phone = "00000000000",
                    profilePicture = "ehe",
                ),
                Contact(
                    name = "Gurdu",
                    surname = "Guzu",
                    phone = "11111111111",
                    profilePicture = "huhu",
                ),
                Contact(
                    name = "Naruto",
                    surname = "Boruto",
                    phone = "99999999999",
                    profilePicture = "kusu",
                ),
                Contact(
                    name = "Burrito",
                    surname = "Dürümto",
                    phone = "12345678901",
                    profilePicture = "kusu",
                ),
            ),
            onAddContactClick = {},
            onNavigatedBack = {},
            isFirstCall = false,
            currentBackStackEntry = null,
        )
    }
}
 */
