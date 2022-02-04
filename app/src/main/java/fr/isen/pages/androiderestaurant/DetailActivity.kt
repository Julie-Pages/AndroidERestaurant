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


        val gsonPretty = GsonBuilder().setPrettyPrinting().create()
        binding.buttonTotalPrice.setOnClickListener{
            var dishWithHowMany  = JSONObject("""{"dish": "$dish", "number": $numberDish}""")
            val jsonTutsListPretty: String = gsonPretty.toJson(dishWithHowMany)

            File(cacheDir.absolutePath+"inBacket.json").writeText(jsonTutsListPretty)

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

            val text = "Ajouté au panier"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()

        }


    }
}
