package com.Jhonnatan.superheropedia

import com.google.gson.annotations.SerializedName

data class SuperHeroDetailResponse(
    @SerializedName("name") val superHeroName:String,
    @SerializedName("powerstats") val superHeroPower:PowerStatsResponse,
    @SerializedName("image") val image:SuperHeroImage
)


data class PowerStatsResponse(@SerializedName("intelligence") val intelligence:String,
                              @SerializedName("strength") val strength:String,
                              @SerializedName("speed") val speed:String,
                              @SerializedName("durability") val durability:String,
                              @SerializedName("power") val power:String,
                              @SerializedName("combat") val combat:String)

data class SuperHeroImage(@SerializedName("url") val url:String)