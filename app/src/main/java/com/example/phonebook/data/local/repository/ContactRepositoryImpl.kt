package com.example.phonebook.data.local.repository

import com.example.phonebook.data.local.ContactDao
import com.example.phonebook.domain.model.Contact
import com.example.phonebook.domain.model.InvalidContactException
import com.example.phonebook.domain.repository.IContactRepository
import com.example.phonebook.domain.util.Constants.INVALID_NAME
import com.example.phonebook.domain.util.Constants.INVALID_PHONE
import com.example.phonebook.domain.util.Constants.INVALID_SURNAME

class ContactRepositoryImpl (
    private val dao: ContactDao
): IContactRepository {
    override suspend fun insertContact(contact: Contact) {
        if (contact.phone.length != 11) {
            throw InvalidContactException(message = INVALID_PHONE)
        }
        if (contact.name == "") {
            throw InvalidContactException(message = INVALID_NAME)
        }
        if (contact.surname == "") {
            throw InvalidContactException(message = INVALID_SURNAME)
        }
        // profilePhoto can be null, so it is not handled
        dao.insertContact(contact)
    }

    override suspend fun deleteContact(contact: Contact) {
        dao.deleteContact(contact)
    }

    override fun getAllContacts(): List<Contact> {
        return dao.getAllContacts()

    }
}