package com.example.proyectodm.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectodm.Admin_Interfaz;
import com.example.proyectodm.ConexionSQLiteHelper;
import com.example.proyectodm.MainActivity;
import com.example.proyectodm.R;
import com.example.proyectodm.utilidades.Utilidades;

public class AddUsuario_Interfaz extends AppCompatActivity {

    EditText campoId, campoPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_usuario_interfaz);

        campoId = (EditText) findViewById(R.id.editText_idUsuario);
        campoPassword = (EditText) findViewById(R.id.editText_idPrecio);

        Button btBack = findViewById(R.id.btCancelar);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchAdminInterfaz();
            }
        });

        Button btAdd = findViewById(R.id.btAdd_Menu);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarUsuario();
            }
        });

    }

    private void registrarUsuario(){
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(this, "base_datos", null, 1);

        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_ID, campoId.getText().toString());
        values.put(Utilidades.CAMPO_PASSWORD, campoPassword.getText().toString());

        Long idResultante = db.insert(Utilidades.TABLA_USUARIO, Utilidades.CAMPO_ID, values);

        Toast.makeText(getApplicationContext(), "Id Registro: "+idResultante,Toast.LENGTH_SHORT).show();
        db.close();
        launchAdminInterfaz();
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

    private void launchAdminInterfaz(){ //esto es para lanzar la interfaz
        Intent intent= new Intent(AddUsuario_Interfaz.this, Admin_Interfaz.class);

        startActivity(intent);

    }

    private void launchMainWindow(){
        Intent intent= new Intent(AddUsuario_Interfaz.this, MainActivity.class );

        startActivity(intent);

    }
}