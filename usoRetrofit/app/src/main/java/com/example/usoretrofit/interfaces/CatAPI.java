package com.example.usoretrofit.interfaces;

import com.example.usoretrofit.models.Cat;
import com.example.usoretrofit.models.Pet;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface CatAPI {
    @GET("facts")
    Call<List<Cat>> getCats();
}
