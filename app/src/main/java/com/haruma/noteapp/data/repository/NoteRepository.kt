package com.haruma.noteapp.data.repository

import com.haruma.noteapp.data.database.dao.NoteDao
import com.haruma.noteapp.data.database.entity.Note
import java.sql.Timestamp

class NoteRepository (
    private val noteDao: NoteDao,
) {

    fun getAll() = noteDao.getAll()

    fun findById(id: Int) = noteDao.findById(id)

    fun findByTitle(title: String) = noteDao.findByTitle(title)

    fun findByContent(content: String) = noteDao.findByContent(content)

    fun findByFavorite(isFavorite: Boolean) = noteDao.findByFavorite(isFavorite)

    fun findByPinned(isPinned: Boolean) = noteDao.findByPinned(isPinned)

    fun findByArchived(isArchived: Boolean) = noteDao.findByArchived(isArchived)

    fun findByTrashed(isTrashed: Boolean) = noteDao.findByTrashed(isTrashed)

    suspend fun insert(note: Note) = noteDao.insert(note)

    suspend fun update(note: Note) = noteDao.update(note)

    suspend fun delete(id: Int) = noteDao.delete(id)
}