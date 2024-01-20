package com.example.phonebook.presentation.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.phonebook.domain.model.Contact

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContactsLazyList(
    grouped: Map<Char, List<Contact>>,
    onDeleteContact: (Contact) -> Unit,
) {
    LazyColumn {
        grouped.forEach { (initial, contactsForInitial) ->
            stickyHeader {
                ContactHeader(initial)
            }

            items(contactsForInitial) { contact: Contact ->
                ContactCard(
                    contact = contact,
                    onDeleteContact = onDeleteContact,
                    )
            }
        }
    }
}