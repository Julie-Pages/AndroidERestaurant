package fr.isen.pages.androiderestaurant

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.pages.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.pages.androiderestaurant.model.*

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



    }
}