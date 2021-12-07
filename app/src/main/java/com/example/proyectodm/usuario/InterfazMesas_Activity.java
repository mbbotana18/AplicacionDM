package com.example.proyectodm.usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.proyectodm.MainActivity;
import com.example.proyectodm.R;

public class InterfazMesas_Activity extends AppCompatActivity {

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