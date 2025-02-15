package sistema.logica.Excepciones;

import java.io.Serializable;
import java.lang.Exception;


public class DestinoNoPerteneceException extends Exception implements Serializable {

	private String mensaje;

    public DestinoNoPerteneceException(String mensaje) {
        this.mensaje = mensaje;
    }

    public String darMensaje() {
        return mensaje;
    }

}
