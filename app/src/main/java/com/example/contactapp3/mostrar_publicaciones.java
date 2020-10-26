package com.example.contactapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class mostrar_publicaciones extends AppCompatActivity {

    private TextView tv_titulo, tv_descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_publicaciones);

        tv_titulo = (TextView) findViewById(R.id.txt_alimentos1);
        tv_descripcion = (TextView) findViewById(R.id.txt_descripcionU);

        AdminSQLiteOpenHelper bd_publicaciones = new AdminSQLiteOpenHelper(this, "publicaciones", null, 1);
        SQLiteDatabase BaseDeDatos = bd_publicaciones.getWritableDatabase();

        String titulo = tv_titulo.getText().toString();
        String nombre_empresa = "";
        String descripcion = "";
        String correo = "";
        String pagina = "";
        String facebook = "";
        String instagram = "";
        String whatsapp = "";


        Cursor fila = BaseDeDatos.rawQuery("select * from publicaciones where categoria=?", new String[]{titulo});

        if (fila.moveToFirst()){
            nombre_empresa = fila.getString(2);
            descripcion = fila.getString(3);
            correo = fila.getString(4);
            pagina = fila.getString(5);
            facebook = fila.getString(6);
            instagram = fila.getString(7);
            whatsapp = fila.getString(8);
        }

        String todo = nombre_empresa + "\n" + descripcion + "\n" + correo + "\n" + pagina + "\n" + facebook + "\n" + instagram + "\n" + whatsapp;

        tv_descripcion.setText(todo);
    }

    public void cerrarSesion(View view){

        Intent i = new Intent(this, MainActivity.class);

        //i.putExtra("dato", editTextName.getText().toString());
        startActivity(i);
    }

    public void volverACategorias(View view){

        Intent i = new Intent(this, categorias_emp.class);

        startActivity(i);
    }

    public void irAEditar(View view){

        Intent i = new Intent(this, actualizar.class);

        startActivity(i);
    }

    public void irABorrar(View view){

        Intent i = new Intent(this, borrar.class);

        startActivity(i);
    }


}