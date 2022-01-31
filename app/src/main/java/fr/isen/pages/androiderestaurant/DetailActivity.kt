package fr.isen.pages.androiderestaurant

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.pages.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.pages.androiderestaurant.model.DishModel
import fr.isen.pages.androiderestaurant.model.DishResultModel
import fr.isen.pages.androiderestaurant.model.ItemsViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textDetailTitle.text = (intent.getSerializableExtra("dish") as DishModel).name_fr
    }
}