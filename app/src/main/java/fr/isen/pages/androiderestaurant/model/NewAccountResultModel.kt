package fr.isen.pages.androiderestaurant.model

import java.io.Serializable

data class NewAccountResultModel(val data : List<UserModel>): Serializable

data class UserModel(val id : Int)
