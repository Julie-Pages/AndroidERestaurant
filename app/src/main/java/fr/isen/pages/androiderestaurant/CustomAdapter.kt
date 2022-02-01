package fr.isen.pages.androiderestaurant

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.squareup.picasso.Picasso
import fr.isen.pages.androiderestaurant.databinding.RvDesignBinding
import fr.isen.pages.androiderestaurant.model.DishModel



class CustomAdapter(val mList: List<DishModel>, val onDishClicked: (DishModel) -> Unit): RecyclerView.Adapter<CustomAdapter.DishViewHolder>() {

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
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {

        val dish = mList[position]
        holder.dishName.text = dish.name_fr
        holder.dishPrice.text = dish.prices[0].price + "€"

        //holder.dishPicture.setImageResource(mList[position].image)
        Picasso.get()
            .load(if (dish.images[0].isNotEmpty()) dish.images[0] else null)
            .error(R.drawable.no_picture)
            .placeholder(R.drawable.no_picture)
            .resize(60,60)
            .into(holder.dishPicture)

        holder.itemView.setOnClickListener{
            onDishClicked(mList[position])
        }

    }

    //return the number of the items in the list
    override fun getItemCount(): Int {
       return mList.size
    }



}
