package sistema.logica.Excepciones;

import java.io.Serializable;
import java.lang.Exception;


public class PrecioMenorCero extends Exception implements Serializable {
	private String mensaje;

    public PrecioMenorCero(String mensaje) {
        this.mensaje = mensaje;
    }

    public String darMensaje() {
        return mensaje;
    }
}

