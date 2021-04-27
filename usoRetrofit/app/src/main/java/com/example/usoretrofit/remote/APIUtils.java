package com.example.usoretrofit.remote;

import com.example.usoretrofit.interfaces.PetAPI;

public class APIUtils {
    public APIUtils() {
    }

    public static  final String API_URL="http://192.168.80.17/"; //ip de la computadora

    public static PetAPI getPetAPI(){
        return RetrofitClient.getClient(API_URL).create(PetAPI.class);
    }


}
