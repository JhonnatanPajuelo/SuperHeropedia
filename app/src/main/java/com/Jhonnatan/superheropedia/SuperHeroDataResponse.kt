package com.Jhonnatan.superheropedia

import com.google.gson.annotations.SerializedName

data class SuperHeroDataResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results") val superhero: List<SuperHeroItemReponse>
)


data class SuperHeroItemReponse(
    @SerializedName("id") val SuperHeroId: String,
    @SerializedName("name") val SuperHeroName: String
)