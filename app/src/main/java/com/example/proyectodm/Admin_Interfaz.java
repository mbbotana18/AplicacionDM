package com.example.proyectodm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.proyectodm.admin.AddCarta_Interfaz;
import com.example.proyectodm.admin.AddUsuario_Interfaz;
import com.example.proyectodm.admin.ListarCarta_Interfaz;
import com.example.proyectodm.admin.ListarUsuario_Interfaz;
import com.example.proyectodm.admin.ModificarCarta_Interfaz;
import com.example.proyectodm.admin.ModificarUsuario_Interfaz;

public class Admin_Interfaz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_interfaz);

        Button button = findViewById(R.id.btAddUsuario); //esto es para que cuando se pulse el boton se inicie la siguiente interfaz
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchInterfazAddUsuario();
            }
        });

        Button button1 = findViewById(R.id.btAddMenu); //esto es para que cuando se pulse el boton se inicie la siguiente interfaz
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchInterfazAddCarta();
            }
        });

        Button button2 = findViewById(R.id.btModUsuario); //esto es para que cuando se pulse el boton se inicie la siguiente interfaz
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchInterfazModUsuario();
            }
        });

        Button button3 = findViewById(R.id.btListUsuario); //esto es para que cuando se pulse el boton se inicie la siguiente interfaz
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchInterfazListUsuario();
            }
        });

        Button button4 = findViewById(R.id.btListCarta); //esto es para que cuando se pulse el boton se inicie la siguiente interfaz
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchInterfazListCarta();
            }
        });

        Button button5 = findViewById(R.id.btModCarta); //esto es para que cuando se pulse el boton se inicie la siguiente interfaz
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchInterfazModCarta();
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

    private void launchInterfazAddUsuario(){ //esto es para lanzar la interfaz
        Intent intent= new Intent(Admin_Interfaz.this, AddUsuario_Interfaz.class);

        startActivity(intent);

    }

    private void launchInterfazAddCarta(){ //esto es para lanzar la interfaz
        Intent intent= new Intent(Admin_Interfaz.this, AddCarta_Interfaz.class);

        startActivity(intent);

    }

    private void launchInterfazModUsuario(){ //esto es para lanzar la interfaz
        Intent intent= new Intent(Admin_Interfaz.this, ModificarUsuario_Interfaz.class);

        startActivity(intent);

    }

    private void launchInterfazModCarta(){ //esto es para lanzar la interfaz
        Intent intent= new Intent(Admin_Interfaz.this, ModificarCarta_Interfaz.class);

        startActivity(intent);

    }

    private void launchInterfazListUsuario(){ //esto es para lanzar la interfaz
        Intent intent= new Intent(Admin_Interfaz.this, ListarUsuario_Interfaz.class);

        startActivity(intent);

    }

    private void launchInterfazListCarta(){ //esto es para lanzar la interfaz
        Intent intent= new Intent(Admin_Interfaz.this, ListarCarta_Interfaz.class);

        startActivity(intent);

    }

    private void launchMainWindow(){
        Intent intent= new Intent(Admin_Interfaz.this, MainActivity.class );

        startActivity(intent);

    }
}