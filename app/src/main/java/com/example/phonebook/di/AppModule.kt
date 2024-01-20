package com.example.phonebook.di

import android.app.Application
import androidx.room.Room
import com.example.phonebook.data.local.ContactDatabase
import com.example.phonebook.data.local.repository.ContactRepositoryImpl
import com.example.phonebook.domain.repository.IContactRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContactDatabase(app: Application): ContactDatabase {

        return Room.databaseBuilder(
            app,
            ContactDatabase::class.java,
            ContactDatabase.DATABASE_NAME,
        ).build()
    }

    @Provides
    @Singleton
    fun provideContactRepository(db: ContactDatabase): IContactRepository {

        return ContactRepositoryImpl(db.contactDao)
    }

}