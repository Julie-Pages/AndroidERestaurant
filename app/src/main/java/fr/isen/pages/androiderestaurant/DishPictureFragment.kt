package fr.isen.pages.androiderestaurant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import fr.isen.pages.androiderestaurant.databinding.FragmentDishPictureBinding


class DishPictureFragment : Fragment() {

    private lateinit var binding: FragmentDishPictureBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDishPictureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("picture_url")?.let { pictureUrl ->
            Picasso.get()
                .load(if (pictureUrl.isNotEmpty()) pictureUrl else null)
                .error(R.drawable.no_picture)
                .placeholder(R.drawable.no_picture)
                .into(binding.bigImageDish)
        }
    }

    companion object{

        fun newInstance(pictureUrl: String) =
            DishPictureFragment().apply {
                arguments = Bundle().apply {
                    putString("picture_url", pictureUrl)
                }
            }
    }
}