package com.example.estrrado_sujeeshtask.Network

import com.example.estrrado_sujeeshtask.data.ProductPojo
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiCall1 {

    @POST("home")
    fun shoplistData(@Body data: JsonObject): Call<ProductPojo>

    companion object {
      var BASEURL = "https://vinshopify.com/uat/api/"

        fun create(): ApiCall1 {
            val client = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

            return Retrofit.Builder()
                .client(client)
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())

                    .build()
                    .create(ApiCall1::class.java)
        }
    }

}