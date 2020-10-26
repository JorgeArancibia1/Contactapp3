package com.example.contactapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class crear_anuncio extends AppCompatActivity {

    private TextView et_categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_anuncio);

        et_categoria = (TextView) findViewById(R.id.txt_alimentosca);

    }

    public void irAPublicar(View view){

        Intent i = new Intent(this, publicar.class);
        i.putExtra("dato", et_categoria.getText().toString());

        startActivity(i);
    }

    public void cerrarSesion(View view){

        Intent i = new Intent(this, MainActivity.class);

        startActivity(i);
    }
}