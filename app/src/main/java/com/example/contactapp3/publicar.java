package com.example.contactapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class publicar extends AppCompatActivity {

    EditText et_nombre, et_descripcion, et_correo, et_pagina, et_facebook, et_instagram, et_whatsapp;
    Button btnPublicar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicar);

        et_nombre=(EditText)findViewById(R.id.et_nombre);
        et_descripcion=(EditText)findViewById(R.id.et_descripcion);
        et_correo=(EditText)findViewById(R.id.et_correo);
        et_pagina=(EditText)findViewById(R.id.et_pagina);
        et_facebook=(EditText)findViewById(R.id.et_facebook);
        et_instagram=(EditText)findViewById(R.id.et_instagram);
        et_whatsapp=(EditText)findViewById(R.id.et_whatsapp);

        btnPublicar=(Button)findViewById(R.id.btnPublicar);

        final AdminSQLiteOpenHelper bd_publicaciones = new AdminSQLiteOpenHelper(this, "publicaciones", null, 1);

        btnPublicar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                bd_publicaciones.ingresarPublicacion(
                    et_nombre.getText().toString(),
                    et_descripcion.getText().toString(),
                    et_correo.getText().toString(),
                    et_pagina.getText().toString(),
                    et_facebook.getText().toString(),
                    et_instagram.getText().toString(),
                    et_whatsapp.getText().toString()
                );

                Toast.makeText(getApplicationContext(), "Publicación creada", Toast.LENGTH_LONG).show();

                et_nombre.setText("");
                et_descripcion.setText("");
                et_correo.setText("");
                et_pagina.setText("");
                et_facebook.setText("");
                et_instagram.setText("");
                et_whatsapp.setText("");
            }
        }
        );

    }

    public void consultaPublicaciones(View v){
        final AdminSQLiteOpenHelper bd_publicaciones = new AdminSQLiteOpenHelper(this, "publicaciones", null, 1);
        SQLiteDatabase bd1 = bd_publicaciones.getWritableDatabase();


        Cursor fila = bd1.rawQuery("select * from publicaciones ", null);

        if (fila.moveToFirst()){
            et_nombre.setText(fila.getString(0));
            et_descripcion.setText(fila.getString(1));
            et_correo.setText(fila.getString(2));
            et_pagina.setText(fila.getString(3));
            et_facebook.setText(fila.getString(4));
            et_instagram.setText(fila.getString(5));
            et_whatsapp.setText(fila.getString(6));
        } else {
            Toast.makeText(this, "No existen publicaciones.", Toast.LENGTH_SHORT).show();
        }
        bd_publicaciones.close();
    }

    public void irAPublicarCategorias(View view){

        Intent i = new Intent(this, categorias_emp.class);

        startActivity(i);
    }

    public void cerrarSesion(View view){

        Intent i = new Intent(this, MainActivity.class);

        startActivity(i);
    }
}