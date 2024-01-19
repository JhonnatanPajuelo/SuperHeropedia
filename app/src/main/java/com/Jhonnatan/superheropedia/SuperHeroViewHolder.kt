package com.Jhonnatan.superheropedia

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.Jhonnatan.superheropedia.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperHeroViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val binding=ItemSuperheroBinding.bind(view)

        fun bind(superheroItemResponse: SuperHeroItemReponse, onItemSelected: (String) -> Unit){
                binding.tvName.text=superheroItemResponse.SuperHeroName

                Picasso.get().load(superheroItemResponse.imageSuperHero.url).into(binding.ivSuperHero)
                itemView.setOnClickListener { onItemSelected(superheroItemResponse.SuperHeroId) }
        }
}