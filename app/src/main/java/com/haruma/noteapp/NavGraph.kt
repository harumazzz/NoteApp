package com.haruma.noteapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.haruma.noteapp.ui.note_detail.NoteDetailScreen
import com.haruma.noteapp.ui.note_list.NoteListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "note_list",

    ) {
        composable("note_list") {
            NoteListScreen(navController = navController)
        }
        composable("note_detail/{noteId}") {
            NoteDetailScreen(navController = navController)
        }
    }
}