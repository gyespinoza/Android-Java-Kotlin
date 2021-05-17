package com.example.usoretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usoretrofit.interfaces.CatAPI;
import com.example.usoretrofit.interfaces.CatImageAPI;
import com.example.usoretrofit.models.Cat;
import com.example.usoretrofit.models.CatImage;
import com.example.usoretrofit.models.Pet;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CatImagesActivity extends AppCompatActivity {
    ImageView image;
    TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_images);


        image=findViewById(R.id.image);
        resultado=findViewById(R.id.resultado);



    }

    public void getImage(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://thatcopy.pw/catapi/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        CatImageAPI catImageAPI = retrofit.create(CatImageAPI.class);

        //llamada http
        Call<CatImage> call = catImageAPI.imageURL();
        call.enqueue(new Callback<CatImage>() {
            @Override
            public void onResponse(Call<CatImage> call, Response<CatImage> response) {
                CatImage catImage= response.body();

                resultado.setText(catImage.getUrl());
            }

            @Override
            public void onFailure(Call<CatImage> call, Throwable t) {
                resultado.setText(t.getMessage());
            }
        });

    }
}