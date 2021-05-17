package com.example.usoretrofit.interfaces;

import com.example.usoretrofit.models.Cat;
import com.example.usoretrofit.models.CatImage;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CatImageAPI {
    @GET("/rest/")
    Call<CatImage> imageURL();
}
