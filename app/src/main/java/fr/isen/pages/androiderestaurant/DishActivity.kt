package fr.isen.pages.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish)

        val category = intent.getStringExtra( "category_type")
        findViewById<TextView>(R.id.choiceDishTitle).text = category
    }
}