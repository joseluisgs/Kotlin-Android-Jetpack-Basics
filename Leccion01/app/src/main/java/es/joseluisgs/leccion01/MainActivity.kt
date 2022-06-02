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
    var actualValue = 0

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
        val btCountUp = binding.btCountUp

        btRoll.setOnClickListener {
            rollDice()
        }

        btCountUp.setOnClickListener {
            countUp()
        }

    }

    private fun countUp() {
        Toast.makeText(this, "Counting up", Toast.LENGTH_SHORT).show()
        actualValue = if (actualValue < 6) actualValue + 1 else 1
        setRollImage()
    }

    private fun rollDice() {
        Toast.makeText(this, "Dice rolled", Toast.LENGTH_SHORT).show()
        // Vamos a obtener la imagen del dado
        setRollImage()
    }

    private fun setRollImage() {
        val drawableResource = getRandomDiceImage()

        // Se la asignamos
        val imgDice = binding.imgDice
        imgDice.setImageResource(drawableResource)
    }

    private fun getRandomDiceImage(): Int {
        val randomInt = (1..6).random()
        this.actualValue = randomInt
        return when (randomInt) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }
}