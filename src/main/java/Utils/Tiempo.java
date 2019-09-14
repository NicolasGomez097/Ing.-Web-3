package Utils;

import javax.persistence.AttributeConverter;

public class Tiempo {
    public Integer hora;
    public Integer minuto;
    public Integer segundo;

    public Tiempo(){}

    public Tiempo(String tiempo){
        this.parse(tiempo);
    }

    public Boolean parse(String tiempo){
        try{

            this.hora = Integer.parseInt(tiempo.split(":")[0]);
            this.minuto = Integer.parseInt(tiempo.split(":")[1]);
            this.segundo = Integer.parseInt(tiempo.split(":")[2]);

            Boolean valido = (
                (this.hora >= 0) &&
                (this.hora <= 23) &&
                (this.minuto >= 0) &&
                (this.minuto <= 59) &&
                (this.segundo >= 0) &&
                (this.segundo <= 59)
            );

            if(valido){
                return true;

            }else{
                this.hora = null;
                this.minuto = null;
                this.segundo = null;

                return false;
            }


        }catch(Exception e){
            this.hora = null;
            this.minuto = null;
            this.segundo = null;

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

                if(a.segundo < b.segundo){
                    return -3;

                }else if(a.segundo == b.segundo){

                    return 0;

                }else if(a.segundo > b.segundo){
                    return 3;
                }

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
    	String salida = hora+":"+minuto+":"+segundo;
    	return salida;
    }
    
    public class TiempoConverter implements AttributeConverter<Tiempo, String> {

    	@Override
    	public String convertToDatabaseColumn(Tiempo attribute) {		
    		return attribute.toString();
    	}

    	@Override
    	public Tiempo convertToEntityAttribute(String dbData) {
    		return new Tiempo(dbData);
    	}

    }
}

