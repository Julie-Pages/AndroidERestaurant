package fr.isen.pages.androiderestaurant.model

import java.io.Serializable

data class NewAccountResultModel(val data : UserModel): Serializable

data class UserModel(val id : Int, val email : String, val password : String)
