package com.example.phonebook.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Contact(
    @PrimaryKey
    val phone: String, // keyboardType == number, len == 11, first 2 numbers 05
    val name: String,
    val surname: String,
    val profilePicture: String,
)

class InvalidContactException(message: String): Exception(message)
