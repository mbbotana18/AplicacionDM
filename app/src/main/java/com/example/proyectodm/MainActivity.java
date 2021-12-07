package com.example.proyectodm;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectodm.usuario.InterfazMesas_Activity;
import com.example.proyectodm.utilidades.Utilidades;

public class MainActivity extends AppCompatActivity {

    EditText campoId, campoPass;
    ConexionSQLiteHelper conexion1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_window);

        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(this, "base_datos", null, 1);

        conexion1 = new ConexionSQLiteHelper(getApplicationContext(), "base_datos", null, 1);
        campoId = (EditText) findViewById(R.id.textUsuario);
        campoPass = (EditText) findViewById(R.id.textPassword);

        Button button = findViewById(R.id.btIniciar); //esto es para que cuando se pulse el boton se inicie la siguiente interfaz
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                launchLogin();
            }
        });
    }


    private void launchAdminInterfaz() {
        Intent intent = new Intent(MainActivity.this, Admin_Interfaz.class);

        startActivity(intent);
    }

    private void launchInterfazMesas() { //esto es para lanzar la interfaz de las mesas
        Intent intent = new Intent(MainActivity.this, InterfazMesas_Activity.class);

        startActivity(intent);

    }

    private void launchLogin() {
        SQLiteDatabase db = conexion1.getReadableDatabase();
        String[] parametros = {campoId.getText().toString()};
        String[] campos = {Utilidades.CAMPO_PASSWORD};
        String admin = "admin";
        try {
            Cursor cursor = db.query(Utilidades.TABLA_USUARIO, campos, Utilidades.CAMPO_ID + "=?", parametros, null, null, null);
            //busca en la base de datos el campo id que coincida con el nombre y recoge los parametros pass
            cursor.moveToFirst();
            String pass = campoPass.getText().toString();
            if (pass.equals(cursor.getString(0))) {      //comprueba si la contraseña escrita y la de la base son la misma
                if (campoId.getText().toString().equals(admin)) {
                    launchAdminInterfaz();
                } else {
                    launchInterfazMesas();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Usuario o contraseña erronea", Toast.LENGTH_LONG).show();
            }
            cursor.close();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Usuario o contraseña erronea", Toast.LENGTH_LONG).show();
        }


    }

}
