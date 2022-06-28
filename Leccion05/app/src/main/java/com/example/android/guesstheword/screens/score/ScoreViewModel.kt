package com.example.android.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// ModelView para SocreFragment
// Al tener los parámetros haremos una factoría para pasarle el parámetro
class ScoreViewModel(finalScore: Int) : ViewModel() {
    // The final score liveData de solo lectura
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    // Para indicar si queremos volver a jugar o no
    private val _eventPlayAgain = MutableLiveData<Boolean>()
    val eventPlayAgain: LiveData<Boolean>
        get() = _eventPlayAgain

    init {
        _score.value = finalScore
        Log.i("ScoreViewModel", "Final score is $finalScore")
    }

    fun onPlayAgain() {
        _eventPlayAgain.value = true
    }

    fun onPlayAgainComplete() {
        _eventPlayAgain.value = false
    }
}