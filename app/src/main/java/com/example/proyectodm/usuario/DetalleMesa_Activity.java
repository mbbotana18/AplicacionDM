package com.example.proyectodm.usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectodm.ConexionSQLiteHelper;
import com.example.proyectodm.MainActivity;
import com.example.proyectodm.R;
import com.example.proyectodm.entidades.Comanda;
import com.example.proyectodm.entidades.Consumicion;
import com.example.proyectodm.utilidades.ComandaConfigurator;
import com.example.proyectodm.utilidades.Utilidades;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DetalleMesa_Activity extends AppCompatActivity {

    ArrayList<String> listaInformacion;
    ArrayList<String> listaInformacionComanda;
    ArrayList<Consumicion> listaConsumicion;
    ArrayList<Consumicion> listaCarta;
    ListView ViewConsumicion;
    int mesa;
    String[] consumiciones = {"PIZZA", "PASTA", "SOPA", "LENTEJAS"};//Esto deberia rellenarse con los valores de la base de datos pero no va
    int[] precios = {7, 8, 5, 6};//Esto deberia rellenarse con los valores de la base de datos pero no va
    ComandaConfigurator c = new ComandaConfigurator(consumiciones, precios);


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

        ViewConsumicion = (ListView) findViewById(R.id.listaComanda);

        //listarCarta(); si se usa este metodo no va la app

        Button btAdd_Consumicion = (Button) findViewById(R.id.btAdd);
        btAdd_Consumicion.setOnClickListener((v) -> {showCartaDialog();});

        listarComanda();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacionComanda);
        ViewConsumicion.setAdapter(adaptador);

        calcularPrecioInicial(listaInformacionComanda);

        Button btCerrarMesa = (Button) findViewById(R.id.btCerrarMesa);
        btCerrarMesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cerrarMesa();
            }
        });


    }


    private void calcularPrecioInicial(ArrayList<String> l){
        TextView textViewPrecioTotal = (TextView) findViewById(R.id.textViewPrecioTotal);
        int aux = 0;
        for (int i = 0; i < l.size(); i++){
            String [] s = l.get(i).split(" -- ");
            aux += Integer.parseInt(s[1]);
        }

        textViewPrecioTotal.setText(aux+"€");

    }

    private void cerrarMesa(){
        ConexionSQLiteHelper conexion2 = new ConexionSQLiteHelper(this, "base_datos", null, 1);
        SQLiteDatabase db = conexion2.getWritableDatabase();
        db.execSQL("DELETE FROM "+Utilidades.TABLA_COMANDA+" WHERE MESA = "+ mesa);
        Toast.makeText(getApplicationContext(), "Mesa Cerrada", Toast.LENGTH_LONG).show();
        db.close();
        launchInterfazMesas();

    }

    //No entendemos por que al usar estos metodos no funciona la aplicacion
    /*private void listarCarta() {
        ConexionSQLiteHelper conexion1 = new ConexionSQLiteHelper(this, "base_datos", null, 1);
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
        obtenerCarta();
    }

    private void obtenerCarta() {

        for (int i = 0; i < listaCarta.size(); i++) {
            consumiciones[i] = listaCarta.get(i).getNombre();
            precios[i] = listaCarta.get(i).getPrecio();
        }
    }*/

    private void listarComanda() {
        ConexionSQLiteHelper conexion1 = new ConexionSQLiteHelper(this, "base_datos", null, 1);
        SQLiteDatabase db = conexion1.getReadableDatabase();

        Consumicion consumicion = null;
        listaConsumicion = new ArrayList<Consumicion>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_COMANDA + " WHERE MESA = "+ mesa, null);

        while (cursor.moveToNext()) {
            consumicion = new Consumicion("a",1);
            consumicion.setNombre(cursor.getString(0));
            consumicion.setPrecio(cursor.getInt(1));
            listaConsumicion.add(consumicion);

        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacionComanda = new ArrayList<String>();

        for (int i = 0; i < listaConsumicion.size(); i++) {
            int f=i+1;
            listaInformacionComanda.add(f+". "+listaConsumicion.get(i).getNombre()+" -- "+listaConsumicion.get(i).getPrecio());
        }
    }

    private void consultarComanda() {
        listaInformacion = new ArrayList<String>();
        for(int i = 0; i < c.selectedConsumiciones.length; i++){
            if(c.selectedConsumiciones[i]){
                listaInformacion.add(consumiciones[i] + " - " + precios[i]);
            }
        }

    }

    private void showCartaDialog() {
        AlertDialog.Builder comanda = new AlertDialog.Builder(this);
        comanda.setTitle("Selecciona las consumiciones:");

        comanda.setMultiChoiceItems(c.consumiciones, c.getSelectedConsumiciones(), new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {
                c.getSelectedConsumiciones()[which] = isChecked;
            }
        });
        comanda.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                updateTotalCost();
                launchInterfazMesas();
            }
        });
        comanda.create().show();
    }

    private void updateTotalCost(){
        TextView totalTextView = (TextView) findViewById(R.id.textViewPrecio);
        int total = c.calcularPrecio();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        totalTextView.setText(decimalFormat.format(total)+"€");

        consultarComanda();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        ViewConsumicion.setAdapter(adaptador);

        registrarComanda(total);
    }

    private void registrarComanda(int total) {
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(this, "base_datos", null, 1);

        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_CONSUMICIONES, listaInformacion.toString());
        values.put(Utilidades.CAMPO_PRECIO_TOTAL, String.valueOf(total));
        values.put(Utilidades.CAMPO_MESA, String.valueOf(mesa));

        Long idResultante = db.insert(Utilidades.TABLA_COMANDA, Utilidades.CAMPO_CONSUMICIONES, values);

        Toast.makeText(getApplicationContext(), "Id Registro: " + idResultante, Toast.LENGTH_SHORT).show();
        db.close();
    }

    private void launchInterfazMesas() { //esto es para lanzar la interfaz de las mesas
        Intent intent = new Intent(this, InterfazMesas_Activity.class);

        startActivity(intent);

    }

}