package es.joseluisgs.leccion02

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ConstraintActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint)

        // Añadimos los eventos
        setListeners()
    }

    private fun makeColored(view: View) {
        // Cambiamos el color
        when (view.id) {
            R.id.txBoxOne -> view.setBackgroundColor(Color.DKGRAY)
            R.id.txBoxTwo -> view.setBackgroundColor(Color.GRAY)
            R.id.txBoxThree -> view.setBackgroundColor(Color.BLUE)
            R.id.txBoxFour -> view.setBackgroundColor(Color.MAGENTA)
            R.id.txBoxFive -> view.setBackgroundColor(Color.BLUE)

            R.id.btRed -> view.setBackgroundResource(R.color.my_red)
            R.id.btYellow -> view.setBackgroundResource(R.color.my_yellow)
            R.id.btGreen -> view.setBackgroundResource(R.color.my_green)

            else -> view.setBackgroundColor(Color.LTGRAY)
        }

    }

    private fun setListeners() {
        // Cremoa los eventos
        val boxOneText = findViewById<TextView>(R.id.txBoxOne)
        val boxTwoText = findViewById<TextView>(R.id.txBoxTwo)
        val boxThreeText = findViewById<TextView>(R.id.txBoxThree)
        val boxFourText = findViewById<TextView>(R.id.txBoxFour)
        val boxFiveText = findViewById<TextView>(R.id.txBoxFive)

        val redButton = findViewById<Button>(R.id.btRed)
        val greenButton = findViewById<Button>(R.id.btGreen)
        val yellowButton = findViewById<Button>(R.id.btYellow)

        val rootConstraintLayout = findViewById<View>(R.id.lyConstraintRoot)

        val clickableViews: List<View> =
            listOf(
                boxOneText, boxTwoText, boxThreeText,
                boxFourText, boxFiveText, rootConstraintLayout,
                redButton, greenButton, yellowButton
            )

        for (item in clickableViews) {
            item.setOnClickListener { makeColored(it) }
        }
    }

}