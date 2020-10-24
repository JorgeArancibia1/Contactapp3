package com.example.contactapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class crear_anuncio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_anuncio);
    }

    public void irAPublicar(View view){

        Intent i = new Intent(this, publicar.class);

        startActivity(i);
    }

    public void cerrarSesion(View view){

        Intent i = new Intent(this, MainActivity.class);

        startActivity(i);
    }
}