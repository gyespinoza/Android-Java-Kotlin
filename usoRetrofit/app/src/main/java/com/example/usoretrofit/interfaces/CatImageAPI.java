package com.example.usoretrofit.interfaces;

import com.example.usoretrofit.models.Cat;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CatImage {
    @GET("/rest/")
    Call<CatImage> imageURL();
}
