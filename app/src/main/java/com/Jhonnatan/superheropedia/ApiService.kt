package com.Jhonnatan.superheropedia


import retrofit2.http.GET

import retrofit2.http.Path
import retrofit2.Response
interface ApiService {

    @GET("api/6872261479538002/search/{name}")
    suspend fun getSuperHeroes(@Path("name") superheroName:String):Response<SuperHeroDataResponse>
}