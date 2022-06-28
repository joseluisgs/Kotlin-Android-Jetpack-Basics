package com.example.android.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.ViewModel

// ModelView para SocreFragment
// Al tener los parámetros haremos una factoría para pasarle el parámetro
class ScoreViewModel(finalScore: Int) : ViewModel() {
    // The final score
    var score = finalScore

    init {
        Log.i("ScoreViewModel", "Final score is $finalScore")
    }
}