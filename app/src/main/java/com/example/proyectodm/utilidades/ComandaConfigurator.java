package com.example.proyectodm.utilidades;

public class ComandaConfigurator {
    public String [] consumiciones;
    public int [] precios;
    public boolean[] selectedConsumiciones;

    public ComandaConfigurator (String[] consumiciones, int[] precios){
        this.consumiciones = consumiciones;
        this.precios = precios;
        selectedConsumiciones = new boolean[consumiciones.length];
    }

    public boolean[] getSelectedConsumiciones(){
        return selectedConsumiciones;
    }

    public void setSelectedConsumiciones(boolean[] selectedConsumiciones){
        this.selectedConsumiciones = selectedConsumiciones;
    }

    public int calcularPrecio(){
        int toret = 0;

        for(int i = 0; i < selectedConsumiciones.length; i++){
            if(selectedConsumiciones[i]){
                toret += precios[i];
            }
        }

        return toret;
    }
}
