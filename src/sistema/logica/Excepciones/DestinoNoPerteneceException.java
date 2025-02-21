package sistema.logica.Excepciones;

import java.lang.Exception;


public class DestinoNoPerteneceException extends Exception {

	private String mensaje;

    public DestinoNoPerteneceException(String mensaje) {
        this.mensaje = mensaje;
    }

    public String darMensaje() {
        return mensaje;
    }

}
