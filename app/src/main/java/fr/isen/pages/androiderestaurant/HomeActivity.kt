package fr.isen.pages.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val entreeButton =findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.button2)
        entreeButton.setOnClickListener {

            val Intent = Intent(this,EntreeActivity::class.java)
            startActivity(Intent)
        }

        val platButton =findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.button)
        platButton.setOnClickListener {

            val Intent = Intent(this,PlatActivity::class.java)
            startActivity(Intent)
        }
    }
}