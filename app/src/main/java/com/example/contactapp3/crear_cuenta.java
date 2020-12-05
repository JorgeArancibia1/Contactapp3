package com.example.contactapp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class crear_cuenta extends AppCompatActivity {

    EditText et_nombrecc, et_contrasenacc, et_confContrasenacc, et_correocc;
    Button btn_crear_cuenta;
    RadioButton rb_persona, rb_empresa;
    private JsonObjectRequest mJsonObjectRequest;
    private RequestQueue mRequestQueue;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);
        mToolbar = findViewById(R.id.id_toobar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_nombrecc=(EditText)findViewById(R.id.txt_nombrecc);
        et_contrasenacc=(EditText)findViewById(R.id.txt_contrasenacc);
        et_confContrasenacc=(EditText)findViewById(R.id.txt_confContrasena);
        et_correocc=(EditText)findViewById(R.id.txt_correocc);
        rb_persona=(RadioButton)findViewById(R.id.rb_persona);
        rb_empresa=(RadioButton)findViewById(R.id.rb_empresa);
        btn_crear_cuenta = (Button)findViewById(R.id.btn_crear_cuenta);

        btn_crear_cuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearCuenta("http://10.0.2.2/android/ingresar.php");
            }
        });
    }

    public void volverAlInicio(View view){

        Intent i = new Intent(this, MainActivity.class);

        startActivity(i);
    }

    private void crearCuenta(String URL) {

        String nombre_ = et_nombrecc.getText().toString();
        String contrasena_ = et_contrasenacc.getText().toString();
        String correo_ = et_correocc.getText().toString();
        String contrasenaRep = et_confContrasenacc.getText().toString();

        String nivel = "";

        if (rb_persona.isChecked() == true){
            nivel = "1";
        } else if (rb_empresa.isChecked() == true) {
            nivel = "2";
        }
        String finalNivel = nivel;

        if (!nombre_.isEmpty() && !contrasena_.isEmpty() && !correo_.isEmpty()){
            if (contrasena_.equals(contrasenaRep)){

                StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
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
                        parametros.put("nombre", nombre_);
                        parametros.put("contrasena", contrasena_);
                        parametros.put("correo", correo_);
                        parametros.put("nivel", finalNivel);

                        return parametros;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);

                et_nombrecc.setText("");
                et_contrasenacc.setText("");
                et_confContrasenacc.setText("");
                et_correocc.setText("");

                Toast.makeText(this, "Usuario creado correctamente.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Las contraseñas deben coincidir.", Toast.LENGTH_LONG).show();

            }
        } else {
            Toast.makeText(this, "Error al crear usuario.", Toast.LENGTH_LONG).show();
        }
    }
}