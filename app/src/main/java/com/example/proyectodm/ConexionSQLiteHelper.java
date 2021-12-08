package com.example.proyectodm;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.proyectodm.utilidades.Utilidades;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {



    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_USUARIOS);
        db.execSQL(Utilidades.CREAR_TABLA_CARTA);
        db.execSQL(Utilidades.CREAR_TABLA_COMANDA);
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_ID, "admin");
        values.put(Utilidades.CAMPO_PASSWORD, "1");
        db.insert(Utilidades.TABLA_USUARIO, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL("DROP TABLE IF EXISTS carta");
        db.execSQL("DROP TABLE IF EXISTS comanda");
        onCreate(db);
    }
}
