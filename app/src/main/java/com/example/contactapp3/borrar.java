package com.example.contactapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class borrar extends AppCompatActivity {

    EditText et_nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar);

        et_nombre = (EditText)findViewById(R.id.et_nombre_borrar);
    }

    public void cerrarSesion(View view){

        Intent i = new Intent(this, MainActivity.class);

        startActivity(i);
    }

    public void borrarPublicacion(View view){

        AdminSQLiteOpenHelper bd_publicaciones = new AdminSQLiteOpenHelper(this, "publicaciones", null, 1);
        SQLiteDatabase BaseDeDatos = bd_publicaciones.getWritableDatabase();

        String nombre = et_nombre.getText().toString();

        if (!nombre.isEmpty()){
            int cantidad = BaseDeDatos.delete("publicaciones", "nombre='" + nombre + "'", null);
            BaseDeDatos.close();

            if (cantidad <= 1){
                Toast.makeText(this, "Publicación eliminada correctamente.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "El nombre de la publicacion ingresada no existe.", Toast.LENGTH_LONG).show();
            }
        } else {
          //  Toast.makeText(this, "Debes introducir el nombre de la publicación.", Toast.LENGTH_LONG).show();
        }
    }

    public void volverAMostrarPublicaciones(View view){

        Intent i = new Intent(this, mostrar_publicaciones.class);

        startActivity(i);
    }
}