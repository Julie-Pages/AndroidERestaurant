package fr.isen.pages.androiderestaurant

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import fr.isen.pages.androiderestaurant.databinding.ActivityDishBinding
import org.json.JSONObject
import com.android.volley.DefaultRetryPolicy
import com.android.volley.RequestQueue
import com.android.volley.toolbox.*
import com.google.gson.Gson
import fr.isen.pages.androiderestaurant.model.DishModel
import fr.isen.pages.androiderestaurant.model.DishResultModel
import java.io.File

class DishActivity : MenuActivity() {

    private lateinit var binding: ActivityDishBinding
    @SuppressLint("ResourceType")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDishBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val category = intent.getStringExtra( "category_type")
        binding.choiceDishTitle.text = category

//webservice

        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val jsonObject = JSONObject()
        jsonObject.put("id_shop","1")

        val request = JsonObjectRequest(
            Request.Method.POST,url,jsonObject,
            { response ->
                var gson= Gson()
                var dishResult = gson.fromJson(response.toString(), DishResultModel::class.java)

                when (binding.choiceDishTitle.text){
                    "Choisissez votre entrée" -> displayDishes(dishResult.data[0].items)
                    "Choisissez votre plat" -> displayDishes(dishResult.data[1].items)
                    "Choisissez votre dessert" -> displayDishes(dishResult.data[2].items)
                }

                //binding.textView.text = "Response: ${dishResult.data[1].items[0].name_fr}"

            }, {
                // Error in request
                Log.e( "","Volley error: $it")
            })

        // Volley request policy, only one time request to avoid duplicate transaction
        request.retryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
            // 0 means no retry
            0, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES = 2
            1f // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )

        // Add the volley post request to the request queue
        queue.add(request)

    }
    private fun displayDishes(dishResult: List<DishModel>) {
//RecyclerView
        // getting the recyclerview by its id
        val recyclerview = binding.rvDish

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = CustomAdapter(dishResult) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("dish", it)
            startActivity(intent)
        }
    }
}

