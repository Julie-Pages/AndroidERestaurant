package fr.isen.pages.androiderestaurant.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DishResultModel(val data: List<CategoryModel>): Serializable //what the post request obtain

data class CategoryModel(val name_fr: String, val items: List<DishModel>) : Serializable // for example "Entrées" or "Plats"

data class DishModel(val name_fr: String, @SerializedName ("images") val images: List<String>, val prices: List<PricesModel>) : Serializable //one of the dish

data class PricesModel(val price: String): Serializable

