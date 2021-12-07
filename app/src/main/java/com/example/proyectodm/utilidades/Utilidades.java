package com.example.proyectodm.utilidades;

public class Utilidades {

    //Constantes tabla Usuarios

    public static final String TABLA_USUARIO = "usuario";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_PASSWORD = "password";

    public static final String CREAR_TABLA_USUARIOS = "CREATE TABLE " +
            ""+TABLA_USUARIO+" ("+CAMPO_ID+" " +
            "TEXT, "+CAMPO_PASSWORD+" INTEGER)";

    //Constantes tabla Carta

    public static final String TABLA_CARTA = "carta";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_PRECIO = "precio";

    public static final String CREAR_TABLA_CARTA = "CREATE TABLE " +
            ""+TABLA_CARTA+" ("+CAMPO_NOMBRE+" " +
            "TEXT, "+CAMPO_PRECIO+" INTEGER)";

    public static final String TABLA_COMANDA = "comanda";
    public static final String CAMPO_CONSUMICIONES = "consumiciones";
    public static final String CAMPO_PRECIO_TOTAL = "precio";
    public static final String CAMPO_MESA = "mesa";

    public static final String CREAR_TABLA_COMANDA = "CREATE TABLE " +
            ""+TABLA_COMANDA+" ("+CAMPO_CONSUMICIONES+" " +
            "TEXT, "+CAMPO_PRECIO_TOTAL+" " +
            "INTEGER, "+CAMPO_MESA+" INTEGER)";




}
