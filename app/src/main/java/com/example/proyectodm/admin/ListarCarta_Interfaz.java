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
import com.example.proyectodm.entidades.Consumicion;
import com.example.proyectodm.utilidades.Utilidades;

import java.util.ArrayList;

public class ListarCarta_Interfaz extends AppCompatActivity {

    ListView listViewCarta;
    ArrayList<String> listaInformacion;
    ArrayList<Consumicion> listaCarta;
    ConexionSQLiteHelper conexion1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_carta_interfaz);

        conexion1 = new ConexionSQLiteHelper(getApplicationContext(), "base_datos", null, 1);
        listViewCarta = (ListView) findViewById(R.id.listaCarta);

        listarCarta();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        listViewCarta.setAdapter(adaptador);
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



    private void listarCarta() {
        SQLiteDatabase db = conexion1.getReadableDatabase();

        Consumicion consumicion = null;
        listaCarta = new ArrayList<Consumicion>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_CARTA, null);

        while (cursor.moveToNext()) {
            consumicion = new Consumicion("a",1);
            consumicion.setNombre(cursor.getString(0));
            consumicion.setPrecio(cursor.getInt(1));
            listaCarta.add(consumicion);

        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();

        for (int i = 0; i < listaCarta.size(); i++) {
            int f=i+1;
            listaInformacion.add(f+". "+listaCarta.get(i).getNombre()+" "+listaCarta.get(i).getPrecio()+"€");
        }
    }

    private void launchMainWindow(){
        Intent intent= new Intent(ListarCarta_Interfaz.this, MainActivity.class );

        startActivity(intent);

    }
}