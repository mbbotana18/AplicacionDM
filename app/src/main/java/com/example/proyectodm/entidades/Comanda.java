package com.example.proyectodm.entidades;

import java.util.ArrayList;

public class Comanda {

    ArrayList<Consumicion> consumiciones;
    int precio;
    int mesa;

    public Comanda(ArrayList<Consumicion> consumiciones, int precio, int mesa) {
        this.consumiciones = consumiciones;
        this.precio = precio;
        this.mesa = mesa;
    }

    public ArrayList<Consumicion> getConsumiciones() {
        return consumiciones;
    }

    public void setConsumiciones(String consumiciones) {
        this.consumiciones.clear();
        String[] cons = consumiciones.split("-");
        for(int i = 0; i < cons.length; i++){
            Consumicion c = new Consumicion(cons[i], 0);
            this.consumiciones.add(c);
        }
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public String ToStringConsumiciones(){
        StringBuilder toret = new StringBuilder();

        for(int i = 0; i < consumiciones.size() -1; i++){
            toret.append(consumiciones.get(i).getNombre().toString())
                    .append("-");
        }
        toret.append(consumiciones.get(consumiciones.size()).getNombre().toString());

        return toret.toString();
    }

    public int getPrecioTotal(){
        int toret = 0;

        for(int i = 0; i < consumiciones.size(); i++){
            toret += consumiciones.get(i).getPrecio();
        }

        return toret;
    }
}
