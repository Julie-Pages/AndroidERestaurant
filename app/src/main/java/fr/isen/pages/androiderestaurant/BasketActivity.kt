package fr.isen.pages.androiderestaurant

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import fr.isen.pages.androiderestaurant.databinding.ActivityBasketBinding
import fr.isen.pages.androiderestaurant.databinding.ActivityHomeBinding
import fr.isen.pages.androiderestaurant.model.Basket
import fr.isen.pages.androiderestaurant.model.DishModel
import fr.isen.pages.androiderestaurant.model.ItemBasket
import java.io.File

class BasketActivity : MenuActivity() {
    private lateinit var binding: ActivityBasketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var jsonFile = File(cacheDir.absolutePath+"/inBacket.json")

        if(jsonFile.exists()) {

            // read file
            var deserializingFile =GsonBuilder().create().fromJson(jsonFile.readText(), Basket::class.java)
            displayDishes(deserializingFile.item)
        }

        binding.deleteBasket.setOnClickListener{
            if (this.getSharedPreferences(getString(R.string.app_prefs), Context.MODE_PRIVATE).getInt(getString(R.string.basket_count), 0) != 0) {
                deleteOfMemory()
                finish()
                startActivity(Intent(this, BasketActivity::class.java))
            }
        }

        binding.buttonOrder.setOnClickListener{

            val idClient = this.getSharedPreferences(getString(R.string.app_prefs), Context.MODE_PRIVATE).getInt(getString(R.string.user_id), 0)
            if (idClient == 0){ //have to login
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }else { //already login

            }

        }

        var price = this.getSharedPreferences(getString(R.string.app_prefs), Context.MODE_PRIVATE).getFloat(getString(R.string.price_total),0.0F)
        binding.priceTotalBasket.text = "Prix total : " + price + " €"
    }

    fun deleteOfMemory() {
        File(cacheDir.absolutePath+"/inBacket.json").delete()
        val sharedPreferences = getSharedPreferences(getString(R.string.app_prefs), MODE_PRIVATE)
        sharedPreferences.edit().putInt(getString(R.string.basket_count), 0).apply()
        sharedPreferences.edit().putFloat(getString(R.string.price_total), 0.0F).apply()
        invalidateOptionsMenu()
    }


    private fun displayDishes(basket: List<ItemBasket>) {
//RecyclerView
        // getting the recyclerview by its id
        val recyclerview = binding.rvDishBasket

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = BasketAdapter(basket) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("dish", it)
            startActivity(intent)
        }
    }
}

