package co.edu.uan.android.finalexample418.services

import co.edu.uan.android.finalexample418.dto.Cat
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CatApi {
    @GET("/api/images/get?format=json&size=med&results_per_page=3")
    suspend fun getCats(): List<Cat>

    companion object {

        private lateinit var instance : CatApi

        fun getInstance() : CatApi {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.thecatapi.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                instance = retrofit.create(CatApi::class.java)
            return instance
        }
    }
}