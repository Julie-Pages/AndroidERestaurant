package fr.isen.pages.androiderestaurant


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import androidx.appcompat.widget.AppCompatButton
import fr.isen.pages.androiderestaurant.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)



//button working

        val starterButton =binding.btnStarter
        val dishButton =binding.btnDish
        val dessertButton =binding.btnDessert


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

    private fun changeActivity( category: String ) {
        val Intent = Intent(this, DishActivity::class.java).putExtra("category_type",category)
        Log.i( "INFO", "End of HomeActivity")
        startActivity(Intent)

    }



}


