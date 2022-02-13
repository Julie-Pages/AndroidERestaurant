package fr.isen.pages.androiderestaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.pages.androiderestaurant.databinding.RvDesignBasketBinding
import fr.isen.pages.androiderestaurant.model.ItemBasket

class BasketAdapter (val basket : List<ItemBasket>, val onBasketClick: (ItemBasket) -> Unit) : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        return BasketViewHolder(
            RvDesignBasketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val basket = basket[position]

        holder.name.text = basket.dish.name_fr

        val price = "Total : ${basket.dish.prices[0].price.toFloat() * basket.numberDish} €"
        holder.price.text = price

        val quantity = "Quantité : ${basket.numberDish}"
        holder.quantity.text = quantity

        Picasso.get()
            .load(if (basket.dish.images[0].isNotEmpty()) basket.dish.images[0] else null)
            .error(R.drawable.no_picture)
            .placeholder(R.drawable.no_picture)
            .resize(60,60)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return basket.size
    }

    class BasketViewHolder(binding : RvDesignBasketBinding) : RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.textChoiceBasket
        val price: TextView = binding.priceChoiceBasket
        val quantity: TextView = binding.quantityChoiceBasket
        val image: ImageView = binding.imageChoiceBasket
    }
}
