package com.example.usoretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.usoretrofit.interfaces.PetAPI;
import com.example.usoretrofit.models.Pet;
import com.example.usoretrofit.remote.APIUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class getAllActivity extends AppCompatActivity {
    PetAPI petAPI;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all);

        petAPI = APIUtils.getPetAPI();
        listView=findViewById(R.id.listview);

        getPets();
    }
    public void getPets(){
        Call<List<Pet>> listCall = petAPI.getPets();
        listCall.enqueue(new Callback<List<Pet>>() {
            @Override
            public void onResponse(Call<List<Pet>> call, Response<List<Pet>> response) {
                List<Pet> petList = response.body();
                String[] pets = new String[petList.size()];

                for(int i=0;i<petList.size();i++){
                    pets[i] = petList.get(i).getName();
                }

                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_expandable_list_item_1, pets));

            }

            @Override
            public void onFailure(Call<List<Pet>> call, Throwable t) {
                Toast.makeText(getAllActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void addPet(View v){
        Intent intent= new Intent(getAllActivity.this, PetActivity.class);
        startActivity(intent);
    }
}