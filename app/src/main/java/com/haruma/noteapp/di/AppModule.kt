package com.haruma.noteapp.di

import android.content.Context
import androidx.room.Room
import com.haruma.noteapp.data.database.NoteDatabase
import com.haruma.noteapp.data.database.dao.NoteDao
import com.haruma.noteapp.data.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(
        @ApplicationContext context: Context
    ): NoteDatabase {
        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "note",
        ).fallbackToDestructiveMigration(false)
        .build()
    }

    @Provides
    @Singleton
    fun provideNoteDao(
        noteDatabase: NoteDatabase
    ) = noteDatabase.noteDao()

    @Provides
    @Singleton
    fun provideNoteRepository(
        noteDao: NoteDao
    ) = NoteRepository(noteDao)

}