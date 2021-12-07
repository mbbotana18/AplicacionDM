package com.example.proyectodm.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
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

public class ModificarCarta_Interfaz extends AppCompatActivity {

    EditText campoPlato, campoPrecio;
    ConexionSQLiteHelper conexion1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_carta_interfaz);

        conexion1 = new ConexionSQLiteHelper(getApplicationContext(), "base_datos", null, 1);
        campoPlato = (EditText) findViewById(R.id.editText_idPlato);
        campoPrecio = (EditText) findViewById(R.id.editText_idPrecio);

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
                eliminarUsuario();
            }
        });


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
        String[] parametros={campoPlato.getText().toString()};
        String[] campos={Utilidades.CAMPO_PRECIO};
        try {
            Cursor cursor = db.query(Utilidades.TABLA_CARTA,campos,Utilidades.CAMPO_NOMBRE+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            campoPrecio.setText(cursor.getString(0));
            cursor.close();
        }  catch(Exception e){
            Toast.makeText(getApplicationContext(), "Plato no registrado", Toast.LENGTH_LONG).show();
            limpiar();
        }


    }

    private void limpiar() {
        campoPlato.setText("");
        campoPrecio.setText("");
    }

    private void modificarUsuario() {
        String vacia ="";
        if(campoPlato.getText().toString().equals(vacia)){
            Toast.makeText(getApplicationContext(), "Introduce plato a modificar", Toast.LENGTH_LONG).show();
        }else {
            SQLiteDatabase db = conexion1.getWritableDatabase();
            String[] parametros = {campoPlato.getText().toString()};
            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_NOMBRE, campoPlato.getText().toString());
            values.put(Utilidades.CAMPO_PRECIO, campoPrecio.getText().toString());
            db.update(Utilidades.TABLA_CARTA, values, Utilidades.CAMPO_NOMBRE + "=?", parametros);
            Toast.makeText(getApplicationContext(), "Plato modificado", Toast.LENGTH_LONG).show();
            db.close();
            limpiar();
        }
    }

    private void eliminarUsuario() {
        String vacia ="";
        if(campoPlato.getText().toString().equals(vacia)){
            Toast.makeText(getApplicationContext(), "Introduce plato a eliminar", Toast.LENGTH_LONG).show();
        }else {
            SQLiteDatabase db = conexion1.getWritableDatabase();
            String[] parametros = {campoPlato.getText().toString()};
            db.delete(Utilidades.TABLA_CARTA, Utilidades.CAMPO_NOMBRE + "=?", parametros);
            Toast.makeText(getApplicationContext(), "Plato eliminado", Toast.LENGTH_LONG).show();
            db.close();
            limpiar();
        }
    }

    private void launchMainWindow(){
        Intent intent= new Intent(ModificarCarta_Interfaz.this, MainActivity.class );

        startActivity(intent);

    }
}