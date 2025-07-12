package com.haruma.noteapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.haruma.noteapp.data.database.dao.NoteDao
import com.haruma.noteapp.data.database.entity.Note

@Database(
    entities = [Note::class],
    version = 1,
)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

}