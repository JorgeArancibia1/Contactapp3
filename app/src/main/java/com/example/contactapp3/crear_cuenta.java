package com.example.contactapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class crear_cuenta extends AppCompatActivity {

    EditText et_nombrecc, et_contrasenacc, et_confContrasenacc, et_correocc;
    Button btn_crear_cuenta;
    RadioButton rb_persona, rb_empresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        et_nombrecc=(EditText)findViewById(R.id.txt_nombrecc);
        et_contrasenacc=(EditText)findViewById(R.id.txt_contrasenacc);
        et_confContrasenacc=(EditText)findViewById(R.id.txt_confContrasena);
        et_correocc=(EditText)findViewById(R.id.txt_correocc);
        rb_persona=(RadioButton)findViewById(R.id.rb_persona);
        rb_empresa=(RadioButton)findViewById(R.id.rb_empresa);
        btn_crear_cuenta = (Button)findViewById(R.id.btn_crear_cuenta);

    }

    public void volverAlInicio(View view){

        Intent i = new Intent(this, MainActivity.class);

        startActivity(i);
    }

    public void crearUsuario(View view){
        AdminSQLiteOpenHelper bd_user = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = bd_user.getWritableDatabase();

        String nombre = et_nombrecc.getText().toString();
        String contrasena = et_contrasenacc.getText().toString();
        String correo = et_correocc.getText().toString();
        String contrasenaRep = et_confContrasenacc.getText().toString();

        String nivel = "";

        if (rb_persona.isChecked() == true){
            nivel = "1";
        } else if (rb_empresa.isChecked() == true) {
            nivel = "2";
        }

        if (!nombre.isEmpty() && !contrasena.isEmpty() && !correo.isEmpty()){

            if (contrasena.equals(contrasenaRep)){
                ContentValues registro = new ContentValues();

                //registro.put("id", 0);
                registro.put("nombre", nombre);
                registro.put("contrasena", contrasena);
                registro.put("correo", correo);
                registro.put("nivel", nivel);

                BaseDeDatos.insert("usuarios", null, registro);

                BaseDeDatos.close();

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