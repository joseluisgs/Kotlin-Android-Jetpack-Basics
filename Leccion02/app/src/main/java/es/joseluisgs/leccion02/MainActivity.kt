package es.joseluisgs.leccion02

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Recuperamos el botón
        val btnDone = findViewById<Button>(R.id.btnDone)
        btnDone.setOnClickListener {
            addNickname(it)
        }

        // Tambien lo podemos hacer de un tirón
        findViewById<TextView>(R.id.tvNickname).setOnClickListener {
            updateNickname(it)
        }
    }

    private fun addNickname(view: View) {
        // Encontramos los elementos de la vista con la que vamos a trabajar
        val editText = findViewById<EditText>(R.id.etNickname)
        val nicknameTextView = findViewById<TextView>(R.id.tvNickname)
        // Asignamos el valor
        nicknameTextView.text = editText.text
        editText.visibility = View.GONE
        view.visibility = View.GONE
        nicknameTextView.visibility = View.VISIBLE
        // Escondemos el teclado automáticamente
        // Hide the keyboard.
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun updateNickname(view: View) {
        val editText = findViewById<EditText>(R.id.etNickname)
        val doneButton = findViewById<Button>(R.id.btnDone)
        // para cambiar la visibilidad
        editText.visibility = View.VISIBLE
        doneButton.visibility = View.VISIBLE
        view.visibility = View.GONE

        // Obtenemos el foco
        editText.requestFocus()
        // Mostramos el teclado
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, 0)
    }
}