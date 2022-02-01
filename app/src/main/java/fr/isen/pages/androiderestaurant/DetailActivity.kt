package fr.isen.pages.androiderestaurant

import android.annotation.SuppressLint
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
import org.json.JSONObject

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dish = (intent.getSerializableExtra("dish") as DishModel)
        binding.textDetailTitle.text = dish.name_fr
        var ingredientStr = dish.ingredients.joinToString(", "){it.name_fr}
        binding.listIngredient.text = ingredientStr
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

        var dishWithHowMany  = JSONObject("""{"dish": dish, "number": numberDish}""")


        //var dishWithHowMany = dishInBacket(dish, numberDish)
        val gsonPretty = GsonBuilder().setPrettyPrinting().create()
        binding.buttonTotalPrice.setOnClickListener{
            val jsonTutsListPretty: String = gsonPretty.toJson(dishWithHowMany)

            File("/data/data/fr.isen.pages.androiderestaurant/cache/inBacket.json").writeText(jsonTutsListPretty)


            val text = "Ajouté au panier"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
        }


    }
}
