package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.ViewModel

// View Model for GameFragment
class GameViewModel : ViewModel() {
    init {
        Log.i("GameViewModel", "GameViewModel created!")
    }

    // Para cuando destruyamos el View Model con su Fragment
    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }
}