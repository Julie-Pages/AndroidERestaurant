package fr.isen.pages.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val entreeButton =findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.button2)
        entreeButton.setOnClickListener {

            val text = "Menu des entrées"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()

            val Intent = Intent(this,EntreeActivity::class.java)
            startActivity(Intent)
        }

        val platButton =findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.button)
        platButton.setOnClickListener {

            val text = "Menu des plats"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()

            val Intent = Intent(this,PlatActivity::class.java)
            startActivity(Intent)
        }

        val dessertButton =findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.button3)
        dessertButton.setOnClickListener {

            val text = "Menu des desserts"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()

            val Intent = Intent(this,DessertActivity::class.java)
            startActivity(Intent)
        }
    }
}