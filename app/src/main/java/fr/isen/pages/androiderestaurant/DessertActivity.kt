package fr.isen.pages.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DessertActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dessert)

        val category = intent.getStringExtra( "category_type")
        findViewById<TextView>(R.id.choiceDessertTitle).text = category
    }
}