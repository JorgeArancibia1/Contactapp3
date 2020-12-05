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

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class mostrar_publicaciones extends AppCompatActivity {

    private TextView tv_titulo, tv_descripcion;

    private RequestQueue mRequestQueue;

    String nombre_empresa = "";
    String descripcion = "";
    String correo = "";
    String pagina = "";
    String facebook = "";
    String instagram = "";
    String whatsapp = "";
    String titulo_ = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_publicaciones);

        tv_titulo = (TextView) findViewById(R.id.txt_alimentos1);
        tv_descripcion = (TextView) findViewById(R.id.txt_descripcionU);

        titulo_ = tv_titulo.getText().toString();


        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest("http://10.0.2.2/android/buscar_por_categoria.php?titulo=" + titulo_, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        nombre_empresa = jsonObject.getString("nombre");
                        descripcion = jsonObject.getString("descripcion");
                        correo = jsonObject.getString("correo");
                        pagina = jsonObject.getString("pagina");
                        facebook = jsonObject.getString("facebook");
                        instagram = jsonObject.getString("instagram");
                        whatsapp = jsonObject.getString("whatsapp");

                        String todo = nombre_empresa + "\n" + descripcion + "\n" + correo + "\n" + pagina + "\n" + facebook + "\n" + instagram + "\n" + whatsapp;
                        tv_descripcion.setText(todo);

                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "ERROR DE CONEXIÓN",Toast.LENGTH_SHORT).show();
            }
        }
        );
        mRequestQueue = Volley.newRequestQueue(this);
        mRequestQueue.add(jsonArrayRequest);


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