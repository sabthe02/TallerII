package sistema.logica.Excepciones;

import java.lang.Exception;


public class ListaPaseosVaciaException extends Exception {
	private String mensaje;

    public ListaPaseosVaciaException(String mensaje) {
        this.mensaje = mensaje;
    }

    public String darMensaje() {
        return mensaje;
    }
}
