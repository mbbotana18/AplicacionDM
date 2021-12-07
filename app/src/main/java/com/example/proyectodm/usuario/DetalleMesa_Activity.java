package com.example.proyectodm.usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.proyectodm.ConexionSQLiteHelper;
import com.example.proyectodm.R;
import com.example.proyectodm.entidades.Comanda;
import com.example.proyectodm.entidades.Consumicion;
import com.example.proyectodm.utilidades.Utilidades;

import java.util.ArrayList;

public class DetalleMesa_Activity extends AppCompatActivity {

    ArrayList<String> listaInformacion;
    ArrayList<Consumicion> listaConsumicion;
    public ConexionSQLiteHelper conex;
    ListView ViewConsumicion;
    int mesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_mesa);
        String idMesa = getIntent().getStringExtra("idMesa");
        switch (idMesa){
            case "Mesa 01":
                mesa = 1;
                break;
            case "Mesa 02":
                mesa = 2;
                break;
            case "Mesa 03":
                mesa = 3;
                break;
            case "Mesa 04":
                mesa = 2;
                break;
            case "Mesa 11":
                mesa = 11;
                break;
            case "Mesa 12":
                mesa = 12;
                break;
            case "Mesa 13":
                mesa = 13;
                break;
            case "Mesa 14":
                mesa = 14;
                break;
            case "Mesa 21":
                mesa = 21;
                break;
            case "Mesa 22":
                mesa = 22;
                break;
            case "Mesa 23":
                mesa = 23;
                break;
            case "Mesa 24":
                mesa = 24;
                break;
            default:
                Toast.makeText(getApplicationContext(),"ALGO HA IDO MAl", Toast.LENGTH_LONG).show();
                break;
        }

        Toast.makeText(getApplicationContext(), "Has elegido mesa: " + mesa, Toast.LENGTH_LONG).show();

        conex = new ConexionSQLiteHelper(getApplicationContext(), "base_datos", null, 1);

        ViewConsumicion = (ListView) findViewById(R.id.listaComanda);

        Button btAdd_Consumicion = (Button) findViewById(R.id.btAdd);
        btAdd_Consumicion.setOnClickListener((v) -> {showCartaDialog();});

        //consultarListaConsumicion();

        /*ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        ViewConsumicion.setAdapter(adaptador);*/


    }

    private void showCartaDialog() {
        AlertDialog.Builder comanda = new AlertDialog.Builder(this);
        comanda.setTitle("Selecciona las consumiciones:");


        comanda.create().show();
    }

    private void consultarListaConsumicion(){
        SQLiteDatabase db = conex.getReadableDatabase();

        Consumicion consumicion = null;
        listaConsumicion = new ArrayList<Consumicion>();

        Cursor cursor = db.rawQuery("SELECT CONSUMICIONES, PRECIO FROM "+Utilidades.TABLA_COMANDA+" WHERE MESA = " +mesa, null);

        while (cursor.moveToNext()){
            consumicion = new Consumicion("a", 1);
            consumicion.setNombre(cursor.getString(0));
            consumicion.setPrecio(cursor.getInt(1));

            listaConsumicion.add(consumicion);
        }
        obtenerLista();
    }

    private void obtenerLista(){
        listaInformacion = new ArrayList<String>();


        for(int i = 0; i < listaConsumicion.size(); i++){
            listaInformacion.add(listaConsumicion.get(i).getNombre()+" - "+listaConsumicion.get(i).getPrecio());
        }

        if(listaInformacion.size() == 0){
            Toast.makeText(getApplicationContext(), "vacio", Toast.LENGTH_LONG);
            listaInformacion.add("Hola");
        }


    }

}