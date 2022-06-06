package es.joseluisgs.leccion02

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import es.joseluisgs.leccion02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // Binding
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)

        // podemos hacerlo así
        // binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // O así
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recuperamos el botón
        binding.btnDone.setOnClickListener {
            addNickname(it)
        }

        // Tambien lo podemos hacer de un tirón
        binding.tvNickname.setOnClickListener {
            updateNickname(it)
        }
    }

    private fun addNickname(view: View) {
        // Asignamos el valor
        binding.tvNickname.text = binding.etNickname.text
        binding.etNickname.visibility = View.GONE
        view.visibility = View.GONE
        binding.tvNickname.visibility = View.VISIBLE
        // Escondemos el teclado automáticamente
        // Hide the keyboard.
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun updateNickname(view: View) {
        // para cambiar la visibilidad
        binding.etNickname.visibility = View.VISIBLE
        binding.btnDone.visibility = View.VISIBLE
        view.visibility = View.GONE

        // Obtenemos el foco
        binding.etNickname.requestFocus()
        // Mostramos el teclado
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.etNickname, 0)
    }
}