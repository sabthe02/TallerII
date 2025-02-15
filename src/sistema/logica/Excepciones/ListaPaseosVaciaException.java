package sistema.logica.Excepciones;

import java.io.Serializable;
import java.lang.Exception;


public class ListaPaseosVaciaException extends Exception implements Serializable {
	private String mensaje;

    public ListaPaseosVaciaException(String mensaje) {
        this.mensaje = mensaje;
    }

    public String darMensaje() {
        return mensaje;
    }
}
