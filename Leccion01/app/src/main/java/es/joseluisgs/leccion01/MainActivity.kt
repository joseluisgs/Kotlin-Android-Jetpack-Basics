package es.joseluisgs.leccion01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import es.joseluisgs.leccion01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // Hacemos el binding de los elementos de la vista
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // sin view binding
        //setContentView(R.layout.activity_main)

        // Para el binding de la vista usando viewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(binding.root)



        // Para obtener los elementos de la vista usamos findViewById
        // Pero esto puede ocasionar problemas de nullPointerException
        // val btRoll: Button = findViewById(R.id.btRoll)
        // val tvResult: TextView = findViewById(R.id.tvResult)

        // Haciendo el binding de los elementos de la vista usaremos el viewBinding
        val btRoll = binding.btRoll
        val tvResult = binding.tvResult
        val btCountUp = binding.btCountUp

        btRoll.setOnClickListener {
            rollDice()
            tvResult.text = (1..6).random().toString()
        }

        btCountUp.setOnClickListener {
            countUp()
            val count =tvResult.text.toString().toIntOrNull() ?: 0
            tvResult.text = (count + 1).toString()
        }

    }

    private fun countUp() {
        Toast.makeText(this, "Counting up", Toast.LENGTH_SHORT).show()
    }

    private fun rollDice() {
        Toast.makeText(this, "Dice rolled", Toast.LENGTH_SHORT).show()
    }
}