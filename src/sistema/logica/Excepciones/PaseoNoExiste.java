package sistema.logica.Excepciones;

import java.io.Serializable;
import java.lang.Exception;

public class PaseoNoExiste extends Exception implements Serializable{

	private String mensaje;

    public PaseoNoExiste(String mensaje) {
        this.mensaje = mensaje;
    }

    public String darMensaje() {
        return mensaje;
    }
}
