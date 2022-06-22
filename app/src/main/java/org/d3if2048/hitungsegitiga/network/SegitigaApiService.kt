package org.d3if2048.hitungsegitiga.network

import com.google.gson.GsonBuilder
import org.d3if2048.hitungsegitiga.model.About
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/vitriaagrn/API_HitungSegitiga/main/"

private val gson = GsonBuilder()
    .setLenient()
    .create()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(gson))
    .baseUrl(BASE_URL)
    .build()

interface SegitigaApiService {
    @GET("API.json")
    suspend fun getSegitiga(): Response<About>
}

object SegitigaApi {
    val service: SegitigaApiService by lazy {
        retrofit.create(SegitigaApiService::class.java)
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED}