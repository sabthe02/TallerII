package sistema.logica.Excepciones;

import java.io.Serializable;
import java.lang.Exception;

public class CantAsientosMayorCeroException extends Exception implements Serializable{

	private String mensaje;

    public CantAsientosMayorCeroException(String mensaje) {
        this.mensaje = mensaje;
    }

    public String darMensaje() {
        return mensaje;
    }
	
}
