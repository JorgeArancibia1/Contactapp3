package com.example.contactapp3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String NOMBRE_BD="bd";
    private static final int VERSION=1;

    private static final String TABLA_PUBLICACIONES="create table publicaciones(id integer primary key autoincrement, categoria text, nombre text, descripcion text, correo text, pagina text, facebook text, instagram text, whatsapp text)";

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        BaseDeDatos.execSQL(TABLA_PUBLICACIONES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void ingresarPublicacion(int id, String categoria, String nombre, String descripcion, String correo, String pagina, String facebook, String instagram, String whatsapp){
        SQLiteDatabase bd = getWritableDatabase(); // Se otorga permiso de escritura a la base de datos.
        if (bd != null){
            //Inserción de datos
            bd.execSQL("insert into publicaciones values(id +'"+categoria+"','"+nombre+"','"+descripcion+"','"+correo+"','"+pagina+"','"+facebook+"','"+instagram+"','"+whatsapp+"')");
            bd.close();
        }
    }
}
