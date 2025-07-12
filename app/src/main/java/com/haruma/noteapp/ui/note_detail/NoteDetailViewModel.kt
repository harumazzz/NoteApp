package com.haruma.noteapp.ui.note_detail

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import com.haruma.noteapp.data.repository.NoteRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haruma.noteapp.data.database.entity.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val repository: NoteRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val noteId: Int = savedStateHandle.get<String>("noteId")?.let {
        Integer.parseInt(it)
    } ?: -1

    private val _title = MutableStateFlow("")

    val title = _title.asStateFlow()

    private val _content = MutableStateFlow("")

    val content = _content.asStateFlow()

    init {
        if (this.noteId != -1) {
            viewModelScope.launch {
                val note = repository.findById(noteId).first()
                _title.value = note.title
                _content.value = note.content
            }
        }
    }

    fun onTitleChange(newTitle: String) {
        _title.value = newTitle
    }

    fun onContentChange(newContent: String) {
        _content.value = newContent
    }

    fun saveNote() {
        viewModelScope.launch {
            val noteToSave = Note(
                id = if (noteId == -1) 0 else noteId,
                title = _title.value.trim(),
                content = _content.value.trim(),
                timestamp = System.currentTimeMillis(),
                color = Color.Yellow.value.toInt(),
                isFavorite = false,
                isPinned = false,
                isArchived =false,
                isTrashed = false
            )
            if (noteId == -1) {
                repository.insert(noteToSave)
            } else {
                repository.update(noteToSave)
            }
        }
    }

}