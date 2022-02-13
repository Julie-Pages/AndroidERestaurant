package fr.isen.pages.androiderestaurant


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.widget.AppCompatButton
import fr.isen.pages.androiderestaurant.databinding.ActivityHomeBinding

class HomeActivity : MenuActivity() {

    private lateinit var binding: ActivityHomeBinding
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


//button working

        val starterButton =binding.imageViewStarter
        val dishButton =binding.imageViewDish
        val dessertButton =binding.imageViewDessert


        starterButton.setOnClickListener {

            //val text = "Menu des entrées"
            //val duration = Toast.LENGTH_SHORT
            //val toast = Toast.makeText(applicationContext, text, duration)
            //toast.show()
            changeActivity(getString(R.string.home_starter))
        }


        dishButton.setOnClickListener {
            changeActivity(getString(R.string.home_dish))
        }


        dessertButton.setOnClickListener {
            changeActivity(getString(R.string.home_dessert))

        }

    }
/*
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.imageBasket -> {
            // User chose the "Settings" item, show the app settings UI...
            startActivity(Intent(this, BasketActivity::class.java))
            true
        }

       /* R.id.action_favorite -> {
            // User chose the "Favorite" action, mark the current item
            // as a favorite...
            true
        }*/

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }*/

    private fun changeActivity( category: String ) {
        val Intent = Intent(this, DishActivity::class.java).putExtra("category_type",category)
        Log.i( "INFO", "End of HomeActivity")
        startActivity(Intent)

    }



}


