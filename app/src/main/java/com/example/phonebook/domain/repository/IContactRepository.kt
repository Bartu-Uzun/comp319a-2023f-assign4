package com.example.phonebook.domain.repository


import com.example.phonebook.domain.model.Contact
import com.example.phonebook.domain.util.Resource

interface IContactRepository {

    suspend fun insertContact(contact: Contact)

    suspend fun deleteContact(contact: Contact)

    fun getAllContacts(): List<Contact>
}