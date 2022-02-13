package fr.isen.pages.androiderestaurant

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class MenuActivity : AppCompatActivity(){

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu,menu)

        val quantity = this.getSharedPreferences(getString(R.string.app_prefs), Context.MODE_PRIVATE).getInt(getString(R.string.basket_count), 0)
        menu.findItem(R.id.menuQuantityBasket).actionView.findViewById<TextView>(R.id.quantityBasket).text = quantity.toString()


        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.imageBasket -> {
            startActivity(Intent(this,BasketActivity::class.java))
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
}

