package com.example.contactapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class categorias_emp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias_emp);
    }

    public void irACrearAnuncio(View view){

        Intent i = new Intent(this, crear_anuncio.class);

        startActivity(i);
    }

    public void volverAlInicio(View view){

        Intent i = new Intent(this, MainActivity.class);

        startActivity(i);
    }

    public void irAMostrarPublicaciones(View view){

        Intent i = new Intent(this, mostrar_publicaciones.class);

        startActivity(i);
    }

}