package com.Jhonnatan.superheropedia

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SuperHeroAdapter(var superListadoList:List<SuperHeroItemReponse> = emptyList(),
    private val onItemSelected:(String)->Unit
    ):RecyclerView.Adapter<SuperHeroViewHolder>() {

    fun updateList(superListadoList: List<SuperHeroItemReponse>){
        this.superListadoList=superListadoList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_superhero,parent,false)
        return SuperHeroViewHolder(view)
    }

    override fun getItemCount(): Int {
        return superListadoList.size
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        holder.bind(superListadoList[position],onItemSelected)

    }
}