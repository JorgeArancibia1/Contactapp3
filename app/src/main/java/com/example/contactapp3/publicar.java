package com.example.contactapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class publicar extends AppCompatActivity {

    EditText et_nombre, et_descripcion, et_correo, et_pagina, et_facebook, et_instagram, et_whatsapp;
    Button btnPublicar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicar);
    }

    public void irAPublicarCategorias(View view) {

        Intent i = new Intent(this, categorias_emp.class);

        startActivity(i);
    }

    public void cerrarSesion(View view) {

        Intent i = new Intent(this, MainActivity.class);

        startActivity(i);
    }

    public void cargarImagen(View view) {
        Toast.makeText(this, "Aqui se cargará esa imagen.", Toast.LENGTH_LONG).show();
    }

    public void crearPublicacion(View view) {

        et_nombre = (EditText) findViewById(R.id.et_nombre);
        et_descripcion = (EditText) findViewById(R.id.et_descripcion);
        et_correo = (EditText) findViewById(R.id.et_correo);
        et_pagina = (EditText) findViewById(R.id.et_pagina);
        et_facebook = (EditText) findViewById(R.id.et_facebook);
        et_instagram = (EditText) findViewById(R.id.et_instagram);
        et_whatsapp = (EditText) findViewById(R.id.et_whatsapp);

        btnPublicar = (Button) findViewById(R.id.btnPublicar);

        String dato = getIntent().getStringExtra("dato");

        String nombre___ = et_nombre.getText().toString();
        String descripcion___ = et_descripcion.getText().toString();
        String correo___ = et_correo.getText().toString();
        String pagina___ = et_pagina.getText().toString();
        String facebook___ = et_facebook.getText().toString();
        String instagram___ = et_instagram.getText().toString();
        String whatsapp___ = et_whatsapp.getText().toString();

        if (!nombre___.isEmpty()){
            //
            StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://10.0.2.2/android/ingresar_publicacion.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(), "Operación Exitosa", Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String,String> getParams() throws AuthFailureError {
                    Map<String,String> parametros=new HashMap<String, String>();
                    parametros.put("categoria", dato);
                    parametros.put("nombre", nombre___);
                    parametros.put("descripcion", descripcion___);
                    parametros.put("correo", correo___);
                    parametros.put("pagina", pagina___);
                    parametros.put("facebook", facebook___);
                    parametros.put("instagram", instagram___);
                    parametros.put("whatsapp", whatsapp___);

                    return parametros;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

            //
            et_nombre.setText("");
            et_descripcion.setText("");
            et_correo.setText("");
            et_pagina.setText("");
            et_facebook.setText("");
            et_instagram.setText("");
            et_whatsapp.setText("");

            Toast.makeText(this, "Publicación creada correctamente.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Ha ocurrido un error.", Toast.LENGTH_SHORT).show();
        }
    }
}