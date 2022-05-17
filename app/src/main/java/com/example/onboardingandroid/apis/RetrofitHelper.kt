package com.example.onboardingandroid.apis

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val BASE_URL = "http://10.0.2.2:8181/"
    fun getInstance(): Retrofit {
        val instance = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return instance

    }


}