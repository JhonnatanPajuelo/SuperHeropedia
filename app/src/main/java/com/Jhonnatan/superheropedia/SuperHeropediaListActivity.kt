package com.Jhonnatan.superheropedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import com.Jhonnatan.superheropedia.databinding.ActivitySuperHeropedialistBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SuperHeropediaListActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySuperHeropedialistBinding
    private lateinit var retrofit: Retrofit
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySuperHeropedialistBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit=getRetrofit()
        initUI()
    }

    private fun initUI() {
         //Cuando en el SearchView ya hayamos escrito y queremos enviar
        binding.svSuperHero.setOnQueryTextListener(object :SearchView.OnQueryTextListener
        {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByname(query.orEmpty())
                
                return false

            }

            override fun onQueryTextChange(newText: String?): Boolean =false
        })
    }

    private fun searchByname(query: String) {
        //Corrutinas
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<SuperHeroDataResponse> =retrofit.create(ApiService::class.java).getSuperHeroes(query)
        }
    }

    //Creamos un objeto de retrofit
    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}