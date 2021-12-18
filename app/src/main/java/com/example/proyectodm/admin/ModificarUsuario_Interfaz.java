package com.example.proyectodm.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectodm.ConexionSQLiteHelper;
import com.example.proyectodm.MainActivity;
import com.example.proyectodm.R;
import com.example.proyectodm.utilidades.Utilidades;

public class ModificarUsuario_Interfaz extends AppCompatActivity {

    EditText campoId, campoPass;
    ConexionSQLiteHelper conexion1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_usuario_interfaz);

        conexion1 = new ConexionSQLiteHelper(getApplicationContext(), "base_datos", null, 1);
        campoId = (EditText) findViewById(R.id.editText_idUsuario);
        campoPass = (EditText) findViewById(R.id.editText_idContraseña);

        Button button = findViewById(R.id.btBuscar); //esto es para que cuando se pulse el boton se inicie la siguiente interfaz
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultar();
            }
        });

        Button button1 = findViewById(R.id.btModificar); //esto es para que cuando se pulse el boton se inicie la siguiente interfaz
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarUsuario();
            }
        });

        Button button2 = findViewById(R.id.btEliminar); //esto es para que cuando se pulse el boton se inicie la siguiente interfaz
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmacionDialog();
            }
        });


    }

    private void showConfirmacionDialog() {
        AlertDialog.Builder confirmacion = new AlertDialog.Builder(this);
        confirmacion.setTitle("¿Seguro que quieres eliminar esto?");

        confirmacion.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                eliminarUsuario();
            }
        });
        confirmacion.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

            }
        });
        confirmacion.create().show();
    }




    public boolean onCreateOptionsMenu(Menu menu){ //este metodo es para crear el menu
        super.onCreateOptionsMenu(menu);

        this.getMenuInflater().inflate(R.menu.actions_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){ //este metodo es para que funcione el menu

        boolean toret = false;

        switch (menuItem.getItemId()){ //se puede hacer con un if pero asi podemos añadir mas cosas al menu
            case R.id.opCerrarSesion:
                launchMainWindow();
                toret = true;
                break;
        }

        return toret;
    }



    private void consultar() {
        SQLiteDatabase db = conexion1.getReadableDatabase();
        String[] parametros={campoId.getText().toString()};
        String[] campos={Utilidades.CAMPO_PASSWORD};
        try {
            Cursor cursor = db.query(Utilidades.TABLA_USUARIO,campos,Utilidades.CAMPO_ID+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            campoPass.setText(cursor.getString(0));
            cursor.close();
        }  catch(Exception e){
            Toast.makeText(getApplicationContext(), "Usuario no registrado", Toast.LENGTH_LONG).show();
            limpiar();
        }


    }

    private void limpiar() {
        campoId.setText("");
        campoPass.setText("");
    }

    private void modificarUsuario() {
        String vacia ="";
        if(campoId.getText().toString().equals(vacia)){
            Toast.makeText(getApplicationContext(), "Introduce usuario a modificar", Toast.LENGTH_LONG).show();
        }else {
            SQLiteDatabase db = conexion1.getWritableDatabase();
            String[] parametros = {campoId.getText().toString()};
            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_ID, campoId.getText().toString());
            values.put(Utilidades.CAMPO_PASSWORD, campoPass.getText().toString());
            db.update(Utilidades.TABLA_USUARIO, values, Utilidades.CAMPO_ID + "=?", parametros);
            Toast.makeText(getApplicationContext(), "Usuario modificado", Toast.LENGTH_LONG).show();
            db.close();
            limpiar();
        }
    }

    private void eliminarUsuario() {
        String vacia ="";
        if(campoId.getText().toString().equals(vacia)){
            Toast.makeText(getApplicationContext(), "Introduce usuario a eliminar", Toast.LENGTH_LONG).show();
        }else {
            SQLiteDatabase db = conexion1.getWritableDatabase();
            String[] parametros = {campoId.getText().toString()};
            db.delete(Utilidades.TABLA_USUARIO, Utilidades.CAMPO_ID + "=?", parametros);
            Toast.makeText(getApplicationContext(), "Usuario eliminado", Toast.LENGTH_LONG).show();
            db.close();
            limpiar();
        }
    }

    private void launchMainWindow(){
        Intent intent= new Intent(ModificarUsuario_Interfaz.this, MainActivity.class );

        startActivity(intent);
        this.finish();

    }
}
