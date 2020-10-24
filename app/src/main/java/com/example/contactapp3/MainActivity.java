package com.example.contactapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName,editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = (EditText) findViewById(R.id.txt_user);
        editTextPassword = (EditText) findViewById(R.id.txt_pass);
    }

    /*
    public void Ingresar(View view){
        String nombre = editTextName.getText().toString();
        String contrasena = editTextPassword.getText().toString();



        if (nombre.length()!=0 && contrasena.length()!=0){
            i.putExtra("dato", editTextName.getText().toString());
            startActivity(i);
        }
    }
    */

    public void irACrearCuenta(View view){

        Intent i = new Intent(this, crear_cuenta.class);

        startActivity(i);
    }

    public void irARecuperar(View view){

        Intent i = new Intent(this, recuperar.class);

        startActivity(i);
    }

    public void validarUsuario(View view){


        AdminSQLiteOpenHelper bd_user = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase DatosDeUsuario = bd_user.getWritableDatabase();

        String nombre = editTextName.getText().toString();
        String contrasena = editTextPassword.getText().toString();
        Intent i = new Intent(this, categorias.class);
        Intent i_emp = new Intent(this, categorias_emp.class);

        if (nombre.length()==0){
            Toast.makeText(this,"Debe ingresar el nombre", Toast.LENGTH_LONG).show();
            return;
        }
        if (contrasena.length()==0){
            Toast.makeText(this, "Debe ingresar una contraseña", Toast.LENGTH_LONG).show();
            return;
        }


            Cursor fila = DatosDeUsuario.rawQuery("select * from usuarios where nommbb, nulaarrrreeggaaloo peero que es lo que está mal? yo veo bien la query D:

            if (fila.moveToFirst()) {

                int id = fila.getInt(0);
                String bd_nombre = fila.getString(1);
                boolean b = contrasena.equals(fila.getString(2));
                String bd_correo = fila.getString(3);
                String bd_nivel = fila.getString(4);


                if (b == true && bd_nivel.equals("1")){
                    startActivity(i);
                } else if ( b == true && bd_nivel.equals("2")){
                    startActivity(i_emp);
                }
                DatosDeUsuario.close();
            } else {
                Toast.makeText(this, "No se encontró ningún usuario registrado con ese nombre.",Toast.LENGTH_LONG).show();
            }
    }

    /*
    public void consultarNivel(View v){
        final AdminSQLiteOpenHelper bd=new AdminSQLiteOpenHelper((getApplicationContext()));
        SQLiteDatabase bd1=bd.getWritableDatabase();


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
        bd.close();
    }
    */
}