package sistema.logica.Excepciones;

import java.io.Serializable;

public class CantidadMayorCero extends Exception implements Serializable {
	private String mensaje;

    public CantidadMayorCero(String mensaje) {
        this.mensaje = mensaje;
    }

    public String darMensaje() {
        return mensaje;
    }
}