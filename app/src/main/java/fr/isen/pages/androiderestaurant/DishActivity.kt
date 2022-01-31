package fr.isen.pages.androiderestaurant

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.isen.pages.androiderestaurant.databinding.ActivityDishBinding


class DishActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDishBinding
    @SuppressLint("ResourceType")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setContentView(R.layout.activity_dish)

        val category = intent.getStringExtra( "category_type")
        binding.choiceDishTitle.text = category


//RecyclerView
        // getting the recyclerview by its id
        val recyclerview = binding.rvDish

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)


        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(ItemsViewModel(R.drawable.fond, "Item " + i, "price"))
        }

        val dishes = listOf(
            ItemsViewModel(R.drawable.fond, "Tomate" , "12"),
            ItemsViewModel(R.drawable.fond, "Truc ", "15")
        )


        // Setting the Adapter with the recyclerview
        recyclerview.adapter = CustomAdapter(data) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("dish", it)
            startActivity(intent)
        }

    }
}