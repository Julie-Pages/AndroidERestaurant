package fr.isen.pages.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.pages.androiderestaurant.databinding.ActivityDishBinding
import fr.isen.pages.androiderestaurant.databinding.ActivityLoginBinding
import fr.isen.pages.androiderestaurant.model.NewAccountResultModel
import fr.isen.pages.androiderestaurant.model.UserModel
import org.json.JSONObject

class LoginActivity : MenuActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textCreateAccountClick.setOnClickListener{ //go to CreateAccount

            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }
        binding.buttonLogin.setOnClickListener{
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()

            val queue = Volley.newRequestQueue(this)
            val url = "http://test.api.catering.bluecodegames.com/user/login"
            val jsonObject = JSONObject()
            jsonObject.put("id_shop",1)
            jsonObject.put("email", email)
            jsonObject.put("password", password)

            val request = JsonObjectRequest(
                Request.Method.POST,url,jsonObject,
                { response ->
                    var gson= Gson()
                    var newAccountResult = gson.fromJson(response.toString(), NewAccountResultModel::class.java)
                    Log.d("","$response")
                    if(newAccountResult.data.id != 0) {
                        val idClient = newAccountResult.data.id
                        val sharedPreferences = getSharedPreferences(getString(R.string.app_prefs), MODE_PRIVATE)
                        sharedPreferences.edit().putInt(getString(R.string.user_id), idClient)
                            .apply()
                        val intent = Intent(this, OrderActivity::class.java)
                        startActivity(intent)
                    }else{
                        val text = "Compte inexistant ou mauvais mot de passe"
                        val duration = Toast.LENGTH_LONG
                        val toast = Toast.makeText(applicationContext, text, duration)
                        toast.show()
                    }
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


    }
}