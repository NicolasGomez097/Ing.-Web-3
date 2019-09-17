package com.iw3.restaurante.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Tiempo implements Comparable<Tiempo>{
    private Integer hora;
    private Integer minuto;

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

    
    @Override
	public int compareTo(Tiempo b) {
        if(this.hora < b.hora) {
            return -1;

        }else if(this.hora == b.hora){

            if(this.minuto < b.minuto){
                return -2;

            }else if(this.minuto == b.minuto){
                return 0;

            }else if(this.minuto > b.minuto){
                return 2;
            }

        }else if(this.hora > b.hora){
            return 1;
        }

		return 0;
	}

    @Override
    public String toString() {
    	String salida = "";
    	
    	if(hora < 10)
    			salida += "0";
    	salida += hora + ":";
    	
    	if(minuto < 10)
			salida += "0";
    	salida += minuto;
		
    	return salida;
    }
    
    @JsonIgnore
    public boolean isValid() {
    	return this.hora!=null;
    }

	public Integer getHora() {
		return hora;
	}

	public void setHora(Integer hora) {
		this.hora = hora;
	}

	public Integer getMinuto() {
		return minuto;
	}

	public void setMinuto(Integer minuto) {
		this.minuto = minuto;
	}
}

