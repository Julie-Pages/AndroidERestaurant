package fr.isen.pages.androiderestaurant

import java.io.Serializable

data class ItemsViewModel(val image: Int, val name: String, val price: String): Serializable {
}
