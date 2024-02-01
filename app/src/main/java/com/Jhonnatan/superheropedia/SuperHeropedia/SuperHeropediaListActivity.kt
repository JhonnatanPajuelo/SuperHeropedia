package com.Jhonnatan.superheropedia.SuperHeropedia

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager

import com.Jhonnatan.superheropedia.databinding.ActivitySuperHeropedialistBinding
import com.Jhonnatan.superheropedia.settings.SettingsActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class SuperHeropediaListActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySuperHeropedialistBinding
    private lateinit var retrofit: Retrofit
    private lateinit var superheroAdapter: SuperHeroAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySuperHeropedialistBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Asignamos a la variable retrofit un objeto RetroFit

        retrofit=getRetrofit()
        initUI()
    }

    private fun initUI() {
        binding.btnSettings.setOnClickListener {
            val intent=Intent(this, SettingsActivity::class.java)

            startActivity(intent)

        }
         //Cuando en el SearchView ya hayamos escrito y queremos enviar
        binding.svSuperHero.setOnQueryTextListener(object :SearchView.OnQueryTextListener
        {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByname(query.orEmpty())
                return false

            }

            override fun onQueryTextChange(newText: String?): Boolean =false
        })

        superheroAdapter= SuperHeroAdapter(){SuperHeroID->navigateToDetail(SuperHeroID)}
        binding.rvSuperHero.setHasFixedSize(true)
        binding.rvSuperHero.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.rvSuperHero.adapter=superheroAdapter

    }

    private fun searchByname(query: String) {
        binding.ProgressBar.isVisible=true

        //Corrutinas
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<SuperHeroDataResponse> =retrofit
                                                            .create(ApiService::class.java)
                                                            .getSuperHeroes(query)
            if(myResponse.isSuccessful){
                //En el hilo principal

                //en el body esta la respuesta
                val response: SuperHeroDataResponse? =myResponse.body()

                if(response!=null){
                    runOnUiThread {
                        superheroAdapter.updateList(response.superhero)
                        binding.ProgressBar.isVisible=false

                    }
                    Log.i("Jhon", response.toString())
                }
            }else{
                Log.i("Jhon","No funca")
            }
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

    private fun navigateToDetail(id:String){
        val intent=Intent(this, DetailSuperHeroActivity::class.java)
        intent.putExtra(DetailSuperHeroActivity.EXTRA_ID,id)
        startActivity(intent)
    }


}