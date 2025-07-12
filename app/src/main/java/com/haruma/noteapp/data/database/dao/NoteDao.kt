package com.haruma.noteapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.haruma.noteapp.data.database.entity.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getAll(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id = :id")
    fun findById(id: Int): Flow<Note>

    @Query("SELECT * FROM note WHERE title LIKE :title")
    fun findByTitle(title: String): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE content LIKE :content")
    fun findByContent(content: String): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE is_favorite = :isFavorite")
    fun findByFavorite(isFavorite: Boolean): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE is_pinned = :isPinned")
    fun findByPinned(isPinned: Boolean): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE is_archived = :isArchived")
    fun findByArchived(isArchived: Boolean): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE is_trashed = :isTrashed")
    fun findByTrashed(isTrashed: Boolean): Flow<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: Note)

    @Query("DELETE FROM note WHERE id = :id")
    suspend fun delete(id: Int)

}