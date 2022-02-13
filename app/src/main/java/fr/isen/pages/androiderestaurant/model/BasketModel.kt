package fr.isen.pages.androiderestaurant.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class  Basket(@SerializedName("items") val item: MutableList<ItemBasket>) : Serializable
data class ItemBasket(@SerializedName("dish") var dish : DishModel, @SerializedName("numberDish") var numberDish : Int) :
    Serializable
