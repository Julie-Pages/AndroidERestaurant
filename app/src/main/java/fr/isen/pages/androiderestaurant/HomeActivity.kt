package fr.isen.pages.androiderestaurant


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log


class HomeActivity : AppCompatActivity() {

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

//button working

        val starterButton =findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btnStarter)
        val dishButton =findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btnDish)
        val dessertButton =findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btnDessert)


        starterButton.setOnClickListener {

            //val text = "Menu des entrées"
            //val duration = Toast.LENGTH_SHORT
            //val toast = Toast.makeText(applicationContext, text, duration)
            //toast.show()
            changeActivity(getString(R.string.home_starter))
        }


        dishButton.setOnClickListener {
            //val text = "Menu des plats"
            //val duration = Toast.LENGTH_SHORT
            //val toast = Toast.makeText(applicationContext, text, duration)
            //toast.show()
            changeActivity(getString(R.string.home_dish))
        }


        dessertButton.setOnClickListener {
            //val text = "Menu des desserts"
            //val duration = Toast.LENGTH_SHORT
            //val toast = Toast.makeText(applicationContext, text, duration)
            //toast.show()
            changeActivity(getString(R.string.home_dessert))

        }


    }

    private fun changeActivity( category: String ) {
        val Intent = Intent(this, DishActivity::class.java).putExtra("category_type",category)
        Log.i( "INFO", "End of HomeActivity")
        startActivity(Intent)

    }



}


