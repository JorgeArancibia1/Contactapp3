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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

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

        String _nombre = et_nombre.getText().toString();
        String _descripcion = et_descripcion.getText().toString();
        String _correo = et_correo.getText().toString();
        String _pagina = et_pagina.getText().toString();
        String _facebook = et_facebook.getText().toString();
        String _instagram = et_instagram.getText().toString();
        String _whatsapp = et_whatsapp.getText().toString();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://10.0.2.2/android/actualizar.php", new Response.Listener<String>() {
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

                parametros.put("descripcion", _descripcion);
                parametros.put("correo", _correo);
                parametros.put("pagina", _pagina);
                parametros.put("facebook", _facebook);
                parametros.put("instagram", _instagram);
                parametros.put("whatsapp", _whatsapp);
                parametros.put("nombre", _nombre);
                System.out.println(parametros);

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        et_nombre.setText("");
        et_descripcion.setText("");
        et_correo.setText("");
        et_pagina.setText("");
        et_facebook.setText("");
        et_instagram.setText("");
        et_whatsapp.setText("");

    }
}