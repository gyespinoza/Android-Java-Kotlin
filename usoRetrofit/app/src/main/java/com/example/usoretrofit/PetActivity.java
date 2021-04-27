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
import com.example.usoretrofit.remote.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetActivity extends AppCompatActivity {
    EditText etId, etName, etAge, etColor;
    TextView tvResponse;
    Button btnSave, btnEdit;

    private PetAPI petAPI;//llamado a la interfaz PetAPI

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);

        etId= findViewById(R.id.et_id);
        etName=findViewById(R.id.et_name);
        etAge=findViewById(R.id.et_age);
        etColor=findViewById(R.id.et_color);
        tvResponse=findViewById(R.id.tvResponse);
        btnSave=findViewById(R.id.btnSave);
        btnEdit=findViewById(R.id.btnEdit);

        petAPI= APIUtils.getPetAPI();

        //boton guardar
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etName.getText().toString().isEmpty() && etAge.getText().toString().isEmpty() &&
                etColor.toString().isEmpty()){
                    Toast.makeText(PetActivity.this, "Los campos no deben quedar vacíos",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                //llamado al metodo
                savePet(etName.getText().toString(), etAge.getText().toString(), etColor.getText().toString());
            }
        });

        //boton actualizar
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPet(etId.getText().toString(),etName.getText().toString(), etAge.getText().toString(), etColor.getText().toString());
            }
        });
    }
    public void savePet(String name, String age, String color){
        Pet pet = new Pet(name, age, color);

        //llamado al metodo de la interfaz PetAPI
        petAPI.savePet(pet).enqueue(new Callback<Pet>() {
            @Override
            public void onResponse(Call<Pet> call, Response<Pet> response) {
                Pet responsePet = response.body();
                String responseString = "Reponse code:" + response.code() +
                        "\nData:"+ pet.toString();

                //mostrar la respuesta en el TextView tvResponse
                showResponse(responseString.toString());

            }
            @Override
            public void onFailure(Call<Pet> call, Throwable t) {
                showResponse(t.getMessage());
            }
        });
    }
    public void editPet(String id, String name, String age, String color){
        Pet pet = new Pet(id, name, age, color);

        //llamado al metodo de la interfaz PetAPI
        petAPI.updatePet(pet).enqueue(new Callback<Pet>() {
            @Override
            public void onResponse(Call<Pet> call, Response<Pet> response) {
                Pet responsePet = response.body();
                String responseString = "Reponse code:" + response.code() +
                        "\nData:"+ pet.toString();

                //mostrar la respuesta en el TextView tvResponse
                showResponse(responseString.toString());

            }
            @Override
            public void onFailure(Call<Pet> call, Throwable t) {
                showResponse(t.getMessage());
            }
        });
    }

    //metodo creado para hacer visible el TextView-tvResponse
    public void showResponse(String response){
        if(tvResponse.getVisibility()==View.GONE){
            tvResponse.setVisibility(View.VISIBLE);
        }
        tvResponse.setText(response);

    }
}