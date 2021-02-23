package com.example.apps;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class imageCaptureActivity extends AppCompatActivity {
    //inicializar variable
    ImageView imageView;
    Button btOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_capture);

        //asignar variable
        imageView= findViewById(R.id.imageView);
        btOpen= findViewById(R.id.btOpen);

        //solicitar permiso de camera
        if (ContextCompat.checkSelfPermission(imageCaptureActivity.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(imageCaptureActivity.this, new String[]{
                    Manifest.permission.CAMERA
            },100 //codigo de permiso de camera
            );
        }
        btOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //abrir camera
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==100){
            //obtener la captura de la image
            Bitmap imagen =(Bitmap) data.getExtras().get("data");

            //colocar image en el ImageView
            imageView.setImageBitmap(imagen);
        }
    }
}