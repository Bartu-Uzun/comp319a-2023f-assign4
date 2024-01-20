package com.example.phonebook.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.phonebook.domain.model.Contact

@Database(
    entities = [Contact::class],
    version = 1
)
abstract class ContactDatabase: RoomDatabase() {

    abstract val contactDao: ContactDao

    companion object {
        const val DATABASE_NAME = "contact_db"
    }
}