package fr.isen.pages.androiderestaurant

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.size
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.pages.androiderestaurant.databinding.ActivityCreateAccountBinding
import fr.isen.pages.androiderestaurant.model.UserModel
import org.json.JSONObject
import java.net.URLEncoder

class CreateAccountActivity : MenuActivity() {

    private lateinit var binding: ActivityCreateAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCreateAccount.setOnClickListener{

            if(validForm()){
                val surname = binding.surnameCreateAccountInput.text.toString()
                val name = binding.nameCreateAccountInput.text.toString()
                val address = binding.addressCreateAccountInput.text.toString()
                val email = binding.emailCreateAccountInput.text.toString()
                val password = binding.passwordCreateAccountInput.text.toString()
                var idClient =0
                //request post
                val queue = Volley.newRequestQueue(this)
                val url = "http://test.api.catering.bluecodegames.com/user/register"
                val jsonObject = JSONObject()
                jsonObject.put("id_shop",1)
                jsonObject.put("firstname", name)
                jsonObject.put("lastname", surname)
                jsonObject.put("address", address)
                jsonObject.put("email", email)
                jsonObject.put("password", password)

                val request = JsonObjectRequest(
                    Request.Method.POST,url,jsonObject,
                    { response ->
                        var gson= Gson()
                        var newAccountResult = gson.fromJson(response.toString(), UserModel::class.java)
                        Log.d("","$response")
                        val email = newAccountResult.email
                        val password = newAccountResult.password
                        idClient = newAccountResult.id
                        val sharedPreferences = getSharedPreferences(getString(R.string.app_prefs), MODE_PRIVATE)
                        sharedPreferences.edit().putString(getString(R.string.user_mail), email).apply()
                        sharedPreferences.edit().putString(getString(R.string.user_password), password).apply()
                        sharedPreferences.edit().putInt(getString(R.string.user_id), idClient).apply()

                        val text = "Compte créé"
                        val duration = Toast.LENGTH_SHORT
                        val toast = Toast.makeText(applicationContext, text, duration)
                        toast.show()

                        val intent = Intent(this, OrderActivity::class.java)
                        this.getSharedPreferences(getString(R.string.app_prefs), MODE_PRIVATE).edit()
                            .remove("UserPassword").apply()
                        startActivity(intent)

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

                if (this.getSharedPreferences(getString(R.string.app_prefs), Context.MODE_PRIVATE).getInt(getString(R.string.user_id), 0) != 0) {
                    val intent = Intent(this, OrderActivity::class.java)
                    this.getSharedPreferences(getString(R.string.app_prefs), MODE_PRIVATE).edit()
                        .remove("UserPassword").apply()
                    startActivity(intent)
                }

            }
        }

        binding.textAlreadyHaveAccountClick.setOnClickListener{

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }
    }

    private fun validForm(): Boolean{
        val errorEmptyField = "Champ vide"
        val errorPasswordNotEnoughLength = "8 caractères minimum"
        val errorInvalidEmail = "email invalide"
        var error = true
        //si le champ est vide
        //Surname
        if (binding.surnameCreateAccountInput.text.toString().trim().isEmpty()){
            binding.surnameCreateAccount.error = errorEmptyField
            error = false
        }else{
            binding.surnameCreateAccount.error = null
        }
        //Name
        if (binding.nameCreateAccountInput.text.toString().trim().isEmpty()){
            binding.nameCreateAccount.error = errorEmptyField
            error = false
        }else{
            binding.nameCreateAccount.error = null
        }
        //Address
        if (binding.addressCreateAccountInput.text.toString().trim().isEmpty()){
            binding.addressCreateAccount.error = errorEmptyField
            error = false
        }else{
            binding.addressCreateAccount.error = null
        }
        //Email
        if (binding.emailCreateAccountInput.text.toString().trim().isEmpty()){
            binding.emailCreateAccount.error = errorEmptyField
            error = false
        }else{
            binding.emailCreateAccount.error = null
        }
        //Password
        if (binding.emailCreateAccountInput.text.toString().trim().isEmpty()){
            binding.emailCreateAccount.error = errorEmptyField
            error = false
        }else{
            binding.emailCreateAccount.error = null
        }

        //if email form is invalid
        if (""".+\@.+\..+""".toRegex().matches(binding.emailCreateAccountInput.text.toString())){

            // Clear error text
            binding.emailCreateAccount.error = null
        }else{
            // Set error text
            binding.emailCreateAccount.error = errorInvalidEmail
            error = false
        }

        //Not enough character password
        if (binding.passwordCreateAccountInput.text.toString().length < 8){
            binding.passwordCreateAccount.error = errorPasswordNotEnoughLength
            error = false
        }else{
            binding.passwordCreateAccount.error = null
        }

        return error
    }

}