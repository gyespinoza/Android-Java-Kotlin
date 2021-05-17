package com.example.usoretrofit.interfaces;


import com.example.usoretrofit.models.Pet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface PetAPI {
    @GET("api/petfilter.php/")
    Call<Pet> find(@Query("id") String id);

    @GET("api/read.php")
    Call<List<Pet>> getPets();

    @POST("api/create.php")
    Call<Pet> savePet(@Body Pet Pet);

    @PUT("api/update.php")
    Call<Pet> updatePet(@Body Pet pet);

    @DELETE("api/delete.php/")
    Call<Pet> deletePet(@Query("id") String id);

}