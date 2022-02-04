package fr.isen.pages.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.size
import fr.isen.pages.androiderestaurant.databinding.ActivityCreateAccountBinding
import java.net.URLEncoder

class CreateAccountActivity : AppCompatActivity() {

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

                //request post


                val text = "Incription finie"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
            }
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