package com.example.proyectodm.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectodm.Admin_Interfaz;
import com.example.proyectodm.ConexionSQLiteHelper;
import com.example.proyectodm.MainActivity;
import com.example.proyectodm.R;
import com.example.proyectodm.entidades.Consumicion;
import com.example.proyectodm.utilidades.Utilidades;

import java.util.ArrayList;

public class ListarCarta_Interfaz extends AppCompatActivity {

    EditText consumiciones;
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
        consumiciones = (EditText) findViewById(R.id.editText_idConsumiciones);

        listarCarta();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        listViewCarta.setAdapter(adaptador);

        this.registerForContextMenu(listViewCarta);

        consumiciones.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adaptador.getFilter().filter((charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu( menu, v, menuInfo );

        this.getMenuInflater().inflate( R.menu.menu_contextual, menu );
        return;
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

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        boolean toret = super.onContextItemSelected(item);

        switch ( item.getItemId() ) {
            case R.id.item_contextual_elimina:
                launchInterfazModCarta();
                toret = true;
                break;
            case R.id.item_contextual_modifica:
                launchInterfazModCarta();
                toret=true;
                break;
        }

        return toret;
    }


    private void launchInterfazModCarta(){ //esto es para lanzar la interfaz
        Intent intent= new Intent(this, ModificarCarta_Interfaz.class);

        startActivity(intent);

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
        this.finish();

    }


}