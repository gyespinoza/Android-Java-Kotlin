package com.example.usoretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usoretrofit.interfaces.PetAPI;
import com.example.usoretrofit.models.Pet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText edtId;
    TextView tvName, tvAge, tvColor;
    Button btnSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edtId = findViewById(R.id.edtId);
        tvName = findViewById(R.id.tvName);
        tvAge = findViewById(R.id.tvAge);
        tvColor = findViewById(R.id.tvColor);
        btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    find(edtId.getText().toString());
            }
        });
    }

    private void find(String cod){
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://192.168.80.17/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        PetAPI petAPI = retrofit.create(PetAPI.class);

        //llamada http
        Call<Pet> call = petAPI.find(cod);
        call.enqueue(new Callback<Pet>() {
            @Override
            public void onResponse(Call<Pet> call, Response<Pet> response) {
                try{
                    if(response.isSuccessful()){
                        Pet pet = response.body();
                        tvName.setText(pet.getName());
                        tvAge.setText(pet.getAge());
                        tvColor.setText(pet.getColor());
                    }
                }catch (Exception ex){
                    Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Pet> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}