package fr.isen.pages.androiderestaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.pages.androiderestaurant.databinding.RvDesignBinding

class CustomAdapter(val mList: List<ItemsViewModel>, val onDishClicked: (ItemsViewModel) -> Unit): RecyclerView.Adapter<CustomAdapter.DishViewHolder>() {

    class DishViewHolder(binding: RvDesignBinding):RecyclerView.ViewHolder(binding.root){
        val dishPicture = binding.imageChoice
        val dishName = binding.textChoice
        val dishPrice = binding.priceChoice

    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val binding = RvDesignBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return DishViewHolder(binding)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {

        holder.dishName.text = mList[position].name
        holder.dishPicture.setImageResource(mList[position].image)
        holder.dishPrice.text = mList[position].price

        holder.itemView.setOnClickListener{
            onDishClicked(mList[position])
        }

    }




    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

}