package sistema.logica.Excepciones;

import java.lang.Exception;

public class CantAsientosMayorCeroException extends Exception {

	private String mensaje;

    public CantAsientosMayorCeroException(String mensaje) {
        this.mensaje = mensaje;
    }

    public String darMensaje() {
        return mensaje;
    }
	
}
