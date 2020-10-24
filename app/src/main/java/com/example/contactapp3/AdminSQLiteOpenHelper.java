package com.example.contactapp3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String NOMBRE_BD="bd";
    private static final int VERSION=1;

    private static final String TABLA_PUBLICACIONES="create table publicaciones(id integer primary key, nombre text, descripcion text, correo text, pagina text, facebook text, instagram text, whatsapp text)";
    private static final String TABLA_USUARIOS="create table usuarios(id integer primary key autoincrement, nombre text, contrasena text, correo text, nivel text)";

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /*public AdminSQLiteOpenHelper(Context context){
        super(context, NOMBRE_BD, null, VERSION);
    }*/

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        BaseDeDatos.execSQL(TABLA_PUBLICACIONES);
        BaseDeDatos.execSQL(TABLA_USUARIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void ingresarPublicacion(String nombre, String descripcion, String correo, String pagina, String facebook, String instagram, String whatsapp){
        SQLiteDatabase bd = getWritableDatabase(); // Se otorga permiso de escritura a la base de datos.
        if (bd != null){
            //Inserción de datos
            bd.execSQL("insert into publicaciones values('"+nombre+"','"+descripcion+"','"+correo+"','"+pagina+"','"+facebook+"','"+instagram+"','"+whatsapp+"')");
            bd.close();
        }
    }

    public void crearUsuario(int id, String nombre, String contrasena, String correo, String nivel){
        SQLiteDatabase bd = getWritableDatabase(); // Se otorga permiso de escritura a la base de datos.
        if (bd != null){
            //Inserción de datos
            bd.execSQL("insert into usuarios values(id +'"+nombre+"','"+contrasena+"','"+correo+"','"+nivel+"')");
            bd.close();
        }
    }
}
