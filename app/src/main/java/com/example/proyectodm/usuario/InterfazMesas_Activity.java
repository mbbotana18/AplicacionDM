package com.example.proyectodm.usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.proyectodm.ConexionSQLiteHelper;
import com.example.proyectodm.MainActivity;
import com.example.proyectodm.R;
import com.example.proyectodm.entidades.Comanda;
import com.example.proyectodm.entidades.Consumicion;
import com.example.proyectodm.utilidades.Utilidades;

import java.util.ArrayList;

public class InterfazMesas_Activity extends AppCompatActivity {

    ArrayList<String> listaInformacionComanda;
    ArrayList<Comanda> listaComandas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz_mesas);

        Button bt01 = (Button) findViewById(R.id.bt01);
        Button bt02 = (Button) findViewById(R.id.bt02);
        Button bt03 = (Button) findViewById(R.id.bt03);
        Button bt04 = (Button) findViewById(R.id.bt04);
        Button bt11 = (Button) findViewById(R.id.bt11);
        Button bt12 = (Button) findViewById(R.id.bt12);
        Button bt13 = (Button) findViewById(R.id.bt13);
        Button bt14 = (Button) findViewById(R.id.bt14);
        Button bt21 = (Button) findViewById(R.id.bt21);
        Button bt22 = (Button) findViewById(R.id.bt22);
        Button bt23 = (Button) findViewById(R.id.bt23);
        Button bt24 = (Button) findViewById(R.id.bt24);

        listarCarta();

        colorBotones(listaInformacionComanda);

        bt01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchDetalleMesa(bt01.getText().toString());
            }
        });

        bt02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchDetalleMesa(bt02.getText().toString());
            }
        });
        bt03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchDetalleMesa(bt03.getText().toString());
            }
        });
        bt04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchDetalleMesa(bt04.getText().toString());
            }
        });
        bt11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchDetalleMesa(bt11.getText().toString());
            }
        });
        bt12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchDetalleMesa(bt12.getText().toString());
            }
        });
        bt13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchDetalleMesa(bt13.getText().toString());
            }
        });
        bt14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchDetalleMesa(bt14.getText().toString());
            }
        });
        bt21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchDetalleMesa(bt21.getText().toString());
            }
        });
        bt22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchDetalleMesa(bt22.getText().toString());
            }
        });
        bt23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchDetalleMesa(bt23.getText().toString());
            }
        });
        bt24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchDetalleMesa(bt24.getText().toString());
            }
        });
    }

    private void colorBotones(ArrayList<String> l) {
        Button bt01 = (Button) findViewById(R.id.bt01);
        Button bt02 = (Button) findViewById(R.id.bt02);
        Button bt03 = (Button) findViewById(R.id.bt03);
        Button bt04 = (Button) findViewById(R.id.bt04);
        Button bt11 = (Button) findViewById(R.id.bt11);
        Button bt12 = (Button) findViewById(R.id.bt12);
        Button bt13 = (Button) findViewById(R.id.bt13);
        Button bt14 = (Button) findViewById(R.id.bt14);
        Button bt21 = (Button) findViewById(R.id.bt21);
        Button bt22 = (Button) findViewById(R.id.bt22);
        Button bt23 = (Button) findViewById(R.id.bt23);
        Button bt24 = (Button) findViewById(R.id.bt24);


        for(int i = 0; i < l.size(); i++){
            String[] s = l.get(i).split(": ");
            switch (s[0]){
                case "Mesa 1":
                        bt01.setBackgroundColor(Color.parseColor("#F44336"));
                    break;
                case "Mesa 2":
                        bt02.setBackgroundColor(Color.parseColor("#F44336"));
                    break;
                case "Mesa 3":
                        bt03.setBackgroundColor(Color.parseColor("#F44336"));
                    break;
                case "Mesa 4":
                        bt04.setBackgroundColor(Color.parseColor("#F44336"));
                    break;
                case "Mesa 11":
                        bt11.setBackgroundColor(Color.parseColor("#F44336"));
                    break;
                case "Mesa 12":
                        bt12.setBackgroundColor(Color.parseColor("#F44336"));
                    break;
                case "Mesa 13":
                        bt13.setBackgroundColor(Color.parseColor("#F44336"));
                    break;
                case "Mesa 14":
                        bt14.setBackgroundColor(Color.parseColor("#F44336"));
                    break;
                case "Mesa 21":
                        bt21.setBackgroundColor(Color.parseColor("#F44336"));
                    break;
                case "Mesa 22":
                        bt22.setBackgroundColor(Color.parseColor("#F44336"));
                    break;
                case "Mesa 23":
                        bt23.setBackgroundColor(Color.parseColor("#F44336"));
                    break;
                case "Mesa 24":
                        bt24.setBackgroundColor(Color.parseColor("#F44336"));
                    break;

                default:
                    Toast.makeText(getApplicationContext(), "Algo ha ido mal", Toast.LENGTH_LONG).show();
            }
        }


    }

    private void listarCarta() {
        ConexionSQLiteHelper conexion1 = new ConexionSQLiteHelper(this, "base_datos", null, 1);
        SQLiteDatabase db = conexion1.getReadableDatabase();

        Comanda comanda = null;
        listaComandas = new ArrayList<Comanda>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_COMANDA, null);

        while (cursor.moveToNext()) {
            comanda = new Comanda(null, 0, 0);
            comanda.setPrecio(cursor.getInt(1));
            comanda.setMesa(cursor.getInt(2));
            listaComandas.add(comanda);

        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacionComanda = new ArrayList<String>();

        for (int i = 0; i < listaComandas.size(); i++) {

            listaInformacionComanda.add("Mesa "+ listaComandas.get(i).getMesa()+": "+ listaComandas.get(i).getPrecio());
        }
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

    private void launchMainWindow(){
        Intent intent= new Intent(InterfazMesas_Activity.this, MainActivity.class );

        startActivity(intent);

    }

    private void launchDetalleMesa(String idMesa){
        Intent intent = new Intent(InterfazMesas_Activity.this, DetalleMesa_Activity.class);
        intent.putExtra("idMesa", idMesa);

        startActivity(intent);
    }
}