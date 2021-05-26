package com.example.semana19retrofitapi

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("breeds")
    fun getCats(): Call<List<Cat>>
}