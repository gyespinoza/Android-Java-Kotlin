package com.example.usoretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usoretrofit.interfaces.CatAPI;
import com.example.usoretrofit.interfaces.PetAPI;
import com.example.usoretrofit.models.Cat;
import com.example.usoretrofit.models.Pet;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CatFactsActivity extends AppCompatActivity {
    ListView listView;
    TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_facts);
        listView=findViewById(R.id.listview);
        resultado=findViewById(R.id.resultado);


        getAllCats();
    }


    public void getAllCats(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://cat-fact.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        CatAPI catAPI = retrofit.create(CatAPI.class);

        //llamada http
        Call<List<Cat>> call = catAPI.getCats();
        call.enqueue(new Callback<List<Cat>>() {
            @Override
            public void onResponse(Call<List<Cat>> call, Response<List<Cat>> response) {

              if(response.isSuccessful()){
                  List<Cat> catList = response.body();
                  String[] cats = new String[catList.size()];

                  for(int i=0;i<catList.size();i++){
                      cats[i] = catList.get(i).getText();
                  }

                  listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                          android.R.layout.simple_expandable_list_item_1, cats));

              }else{
                  Toast.makeText(CatFactsActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                  resultado.setText(response.body().toString());
              }



            }

            @Override
            public void onFailure(Call<List<Cat>> call, Throwable t) {
                Toast.makeText(CatFactsActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                resultado.setText(t.getMessage());

            }
        });
    }
}