package com.example.contactapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName,editTextPassword;
    Button btn_login;

    RequestQueue requestQueue;
    String n = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = (EditText) findViewById(R.id.txt_user);
        editTextPassword = (EditText) findViewById(R.id.txt_pass);
        btn_login = (Button)findViewById(R.id.button_login);
        btn_login.setOnClickListener(this);

    }

    public void irACrearCuenta(View view){

        Intent i = new Intent(this, crear_cuenta.class);

        startActivity(i);
    }

    public void irARecuperar(View view){

        Intent i = new Intent(this, recuperar.class);

        startActivity(i);
    }

    public void validarUsuario(){

        String nombre__ = editTextName.getText().toString();
        String contrasena__ = editTextPassword.getText().toString();
        Intent i = new Intent(this, categorias.class);
        Intent i_emp = new Intent(this, categorias_emp.class);
        Toast.makeText(getApplicationContext(),"Soy una prueba", Toast.LENGTH_SHORT).show();
        if (nombre__.length()==0){
            Toast.makeText(getApplicationContext(),"Debe ingresar el nombre", Toast.LENGTH_SHORT).show();
            return;
        }
        if (contrasena__.length()==0){
            Toast.makeText(getApplicationContext(), "Debe ingresar una contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest("http://10.0.2.2/android/buscar_usuario.php?nombre=" + nombre__, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        n = jsonObject.getString("nivel");

                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                if (n.equals("1")){
                    startActivity(i);
                } else if (n.equals("2")) {
                    startActivity(i_emp);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "ERROR DE CONEXIÓN",Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }

    @Override
    public void onClick(View v) {
        if(v == btn_login){
            validarUsuario();
        }
    }
}