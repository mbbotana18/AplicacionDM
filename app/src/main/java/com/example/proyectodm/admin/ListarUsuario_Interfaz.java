package com.example.proyectodm.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.proyectodm.ConexionSQLiteHelper;
import com.example.proyectodm.MainActivity;
import com.example.proyectodm.R;
import com.example.proyectodm.entidades.Usuario;
import com.example.proyectodm.utilidades.Utilidades;

import java.util.ArrayList;

public class ListarUsuario_Interfaz extends AppCompatActivity {

    ListView listViewUsuarios;
    ArrayList<String> listaInformacion;
    ArrayList<Usuario> listaUsuarios;
    ConexionSQLiteHelper conexion1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuario_interfaz);

        conexion1 = new ConexionSQLiteHelper(getApplicationContext(), "base_datos", null, 1);
        listViewUsuarios = (ListView) findViewById(R.id.listaUsuarios);

        listarPersonas();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        listViewUsuarios.setAdapter(adaptador);
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



    private void listarPersonas() {
        SQLiteDatabase db = conexion1.getReadableDatabase();

        Usuario usuario = null;
        listaUsuarios = new ArrayList<Usuario>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_USUARIO, null);

        while (cursor.moveToNext()) {
            usuario = new Usuario("paco", 1);
            usuario.setId(cursor.getString(0));
            usuario.setPassword(cursor.getInt(1));
            listaUsuarios.add(usuario);

        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();

        for (int i = 0; i < listaUsuarios.size(); i++) {
            listaInformacion.add(i+". "+listaUsuarios.get(i).getId());
        }
    }

    private void launchMainWindow(){
        Intent intent= new Intent(ListarUsuario_Interfaz.this, MainActivity.class );

        startActivity(intent);

    }
}







