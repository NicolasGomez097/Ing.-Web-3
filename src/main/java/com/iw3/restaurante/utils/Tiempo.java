package com.iw3.restaurante.utils;

public class Tiempo {
    public Integer hora;
    public Integer minuto;

    public Tiempo(){}

    public Tiempo(String tiempo){
        this.parse(tiempo);
    }

    public Boolean parse(String tiempo){
        try{

            this.hora = Integer.parseInt(tiempo.split(":")[0]);
            this.minuto = Integer.parseInt(tiempo.split(":")[1]);

            Boolean valido = (
                (this.hora >= 0) &&
                (this.hora <= 23) &&
                (this.minuto >= 0) &&
                (this.minuto <= 59)
            );

            if(valido){
                return true;

            }else{
                this.hora = null;
                this.minuto = null;

                return false;
            }


        }catch(Exception e){
            this.hora = null;
            this.minuto = null;

            return false;
        }
    }

    static public Integer comparar(Tiempo a, Tiempo b){

        if((a == null) || (b == null)){
            return null;
        }

        if(a.hora < b.hora) {
            return -1;

        }else if(a.hora == b.hora){

            if(a.minuto < b.minuto){
                return -2;

            }else if(a.minuto == b.minuto){
                return 0;

            }else if(a.minuto > b.minuto){
                return 2;
            }

        }else if(a.hora > b.hora){
            return 1;
        }

        return null;
    }
    
    @Override
    public String toString() {
    	String salida = "";
    	
    	if(hora < 10)
    			salida += "0";
    	salida += hora + ":";
    	
    	if(minuto < 10)
			salida += "0";
    	salida += minuto + ":";
		
    	return salida;
    }
}

