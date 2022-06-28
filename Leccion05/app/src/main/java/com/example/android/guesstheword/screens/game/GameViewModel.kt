package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// View Model for GameFragment
class GameViewModel : ViewModel() {
    // Nuestro modelo de datos
    // Usaremos LiveData para los patrones de observación de datos
    // The current word
    val word = MutableLiveData<String>()

    // The current score
    val score = MutableLiveData<Int>()

    // The list of words - the front of the list is the next word to guess
    lateinit var wordList: MutableList<String>

    init {
        // Iniciamos los datos del modelo
        word.value = ""
        score.value = 0
        // Limpiamos los datos
        resetList()
        nextWord()

        Log.i("GameViewModel", "GameViewModel created!")
    }

    // Para cuando destruyamos el View Model con su Fragment
    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }

    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    /** Methods for buttons presses **/
    fun onSkip() {
        score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        score.value = (score.value)?.plus(1)
        nextWord()
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        if (!wordList.isEmpty()) {
            //Select and remove a word from the list
            word.value = wordList.removeAt(0)
        }
    }


}