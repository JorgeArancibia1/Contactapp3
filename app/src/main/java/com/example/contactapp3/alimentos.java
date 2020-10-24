package com.example.contactapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class alimentos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alimentos);
    }

    public void cerrarSesion(View view){

        Intent i = new Intent(this, MainActivity.class);

        //i.putExtra("dato", editTextName.getText().toString());
        startActivity(i);
    }

    public void volverACategorias(View view){

        Intent i = new Intent(this, categorias.class);

        startActivity(i);
    }
}