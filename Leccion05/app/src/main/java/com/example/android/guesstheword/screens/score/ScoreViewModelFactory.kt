package com.example.android.guesstheword.screens.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// Factoría para crear ScoreViewModel
// Debemos hacer la factoría para pasarle los parámetros
class ScoreViewModelFactory(private val finalScore: Int) : ViewModelProvider.Factory {
    // Debemos crear una clase que implemente ViewModelProvider.Factory

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScoreViewModel::class.java)) {
            return ScoreViewModel(finalScore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}