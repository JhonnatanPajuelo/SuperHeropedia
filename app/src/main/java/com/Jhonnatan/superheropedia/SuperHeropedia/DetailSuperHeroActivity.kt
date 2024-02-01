package com.Jhonnatan.superheropedia.SuperHeropedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import com.Jhonnatan.superheropedia.databinding.ActivityDetailSuperHeroBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class DetailSuperHeroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailSuperHeroBinding
    companion object {
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailSuperHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()
        getSuperHeroInformation(id)
    }

    private fun getSuperHeroInformation(id:String){
        CoroutineScope(Dispatchers.IO).launch {
            val superHeroDetail: Response<SuperHeroDetailResponse> =getRetrofit().create(ApiService::class.java).getSuperHeroDetails(id)
            if(superHeroDetail.body()!=null){
                //Estoy seguro que no es nulo con --> !!
                runOnUiThread { createUI(superHeroDetail.body()!!) }

            }

        }

    }

    private fun createUI(superHero: SuperHeroDetailResponse) {
        Picasso.get().load(superHero.image.url).into(binding.IvSuperHero);
        //binding.tvNameHero.text=superHero.superHeroName
        //binding.tvFullName.text=superHero.biografy.fullName
        //binding.tvPubliser.text=superHero.biografy.publiser
        //Fun

        updateBinding(binding.tvNameHero,superHero.superHeroName)
        updateBinding(binding.tvFullName,superHero.biografy.fullName)
        updateBinding(binding.tvPubliser,superHero.biografy.publiser)
        preparateStats(superHero.superHeroPower)

    }

    private fun updateBinding(texView: TextView,string:String){
        texView.text=string
    }

    private fun preparateStats(superHeroPower: PowerStatsResponse) {
        updateHeigt(binding.vcombat,superHeroPower.combat)
        updateHeigt(binding.vdurability,superHeroPower.durability )
        updateHeigt(binding.vpower,superHeroPower.power )
        updateHeigt(binding.vintelligence,superHeroPower.intelligence )
        updateHeigt(binding.vstrength,superHeroPower.strength )
        updateHeigt(binding.vspeed,superHeroPower.speed )
    }
    private fun updateHeigt(view:View,stat:String){
        val params =view.layoutParams
        //el px lo pasamos a la funcion que convierte en Float
        params.height=pxToDp(stat.toFloat())
        view.layoutParams=params
    }
    //Convertir px a DP
    private fun pxToDp(px:Float):Int{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,px,resources.displayMetrics).roundToInt()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}
