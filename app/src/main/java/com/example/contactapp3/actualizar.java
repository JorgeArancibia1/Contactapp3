package com.example.contactapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class actualizar extends AppCompatActivity {

    private EditText et_nombre, et_descripcion, et_correo, et_pagina, et_facebook, et_instagram, et_whatsapp;
    //Button btn_actualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);

        et_nombre = (EditText) findViewById(R.id.et_nombreac);
        et_descripcion = (EditText) findViewById(R.id.et_descripcionac);
        et_correo = (EditText) findViewById(R.id.et_correoac);
        et_pagina = (EditText) findViewById(R.id.et_paginaac);
        et_facebook = (EditText) findViewById(R.id.et_facebookac);
        et_instagram = (EditText) findViewById(R.id.et_instagramac);
        et_whatsapp = (EditText) findViewById(R.id.et_whatsappac);
    }

    public void cerrarSesion(View view) {

        Intent i = new Intent(this, MainActivity.class);

        startActivity(i);
    }

    public void irAMostrarPublicaciones(View view) {

        Intent i = new Intent(this, mostrar_publicaciones.class);

        startActivity(i);
    }

    public void actualizarPublicacion(View view) {
        AdminSQLiteOpenHelper bd_publicaciones = new AdminSQLiteOpenHelper(this, "publicaciones", null, 1);
        SQLiteDatabase BaseDeDatos = bd_publicaciones.getWritableDatabase();

        String nombre = et_nombre.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String correo = et_correo.getText().toString();
        String pagina = et_pagina.getText().toString();
        String facebook = et_facebook.getText().toString();
        String instagram = et_instagram.getText().toString();
        String whatsapp = et_whatsapp.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("nombre", nombre);
        registro.put("descripcion", descripcion);
        registro.put("correo", correo);
        registro.put("pagina", pagina);
        registro.put("facebook", facebook);
        registro.put("instagram", instagram);
        registro.put("whatsapp", whatsapp);

        int cantidad = BaseDeDatos.update("publicaciones", registro, "nombre='" + nombre + "'", null);
        BaseDeDatos.close();

        if (cantidad <= 1){
            Toast.makeText(this, "Publicación actualizada correctamente.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Ha ocurrido un error al actualizar.", Toast.LENGTH_LONG).show();
        }


        et_nombre.setText("");
        et_descripcion.setText("");
        et_correo.setText("");
        et_pagina.setText("");
        et_facebook.setText("");
        et_instagram.setText("");
        et_whatsapp.setText("");

        //Toast.makeText(this, "Publicación actualizada correctamente.", Toast.LENGTH_LONG).show();
    }
}