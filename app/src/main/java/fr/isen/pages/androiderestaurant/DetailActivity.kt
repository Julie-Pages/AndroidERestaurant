package fr.isen.pages.androiderestaurant

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import fr.isen.pages.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.pages.androiderestaurant.model.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import java.io.File
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.json.Json
import org.json.JSONObject
import java.io.Serializable
import kotlinx.serialization.*
import kotlinx.serialization.json.*

data class  Basket(@SerializedName("items") val item: MutableList<ItemBasket>) : Serializable
data class ItemBasket(@SerializedName("dish") var dish : DishModel, @SerializedName("numberDish") var numberDish : Int) : Serializable

class DetailActivity : MenuActivity() {



    private lateinit var binding: ActivityDetailBinding
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dish = (intent.getSerializableExtra("dish") as DishModel)
        binding.textDetailTitle.text = dish.name_fr
        var ingredientStr = dish.ingredients.joinToString(", "){it.name_fr}
        binding.listIngredient.text = "Contient : " + ingredientStr
        binding.allImageDish.adapter = DishPictureAdaptater(this,dish.images)

        binding.numberTotal.text = "1"
        var numberDish =  1
        binding.buttonTotalPrice.text = "Ajouter au panier : " + (dish.prices[0].price.toFloat() * numberDish ) + "€"

        //add
        binding.addButton.setOnClickListener{
            numberDish += 1
            binding.numberTotal.text = "" + numberDish
            binding.buttonTotalPrice.text = "Ajouter au panier : " + (dish.prices[0].price.toFloat() * numberDish )+ "€"

        }
        //minus
        binding.minusButton.setOnClickListener{
            if (numberDish > 0) {
                numberDish -= 1
                binding.numberTotal.text = "" + numberDish
                binding.buttonTotalPrice.text = "Ajouter au panier : " + (dish.prices[0].price.toFloat() * numberDish)+ "€"
            }
        }

        binding.buttonTotalPrice.setOnClickListener{
            var jsonFile = File(cacheDir.absolutePath+"/inBacket.json")

            if(jsonFile.exists()){

                // read file
                var deserializingFile =GsonBuilder().create().fromJson(jsonFile.readText(), Basket::class.java)
                //desierializer

                deserializingFile.item.firstOrNull { it.dish == dish }?.let {
                    it.numberDish += numberDish
                } ?: run {
                    deserializingFile.item.add(ItemBasket(dish, numberDish))
                }
                //reserialize
                saveInMemory(deserializingFile, jsonFile)

            }else{
                val basket = Basket(mutableListOf(ItemBasket(dish, numberDish)))
                saveInMemory(basket,jsonFile)
            }


            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

            val text = "Ajouté au panier"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
        }

    }

    private fun saveInMemory(basket: Basket, file: File) {
        saveDishCount(basket)
        file.writeText(GsonBuilder().create().toJson(basket))
    }

    private fun saveDishCount(basket: Basket) {
        val count = basket.item.sumOf { it.numberDish }

        val sharedPreferences = getSharedPreferences(getString(R.string.app_prefs), MODE_PRIVATE)
        sharedPreferences.edit().putInt(getString(R.string.basket_count), count).apply()
        invalidateOptionsMenu()
    }

}



