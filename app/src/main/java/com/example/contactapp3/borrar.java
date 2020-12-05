package com.example.contactapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
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

        String nombre = et_nombre.getText().toString();

        if (!nombre.isEmpty()){
            StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://10.0.2.2/android/borrar.php", new Response.Listener<String>() {
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

                    parametros.put("nombre", nombre);

                    return parametros;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

            et_nombre.setText("");

        } else {
          Toast.makeText(this, "Debes introducir el nombre de la publicación.", Toast.LENGTH_LONG).show();
        }
    }

    public void volverAMostrarPublicaciones(View view){

        Intent i = new Intent(this, mostrar_publicaciones.class);

        startActivity(i);
    }
}