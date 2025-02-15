package sistema.logica.Excepciones;

import java.io.Serializable;
import java.lang.Exception;


public class MenorDe0 extends Exception implements Serializable {
	private String mensaje;

    public MenorDe0(String mensaje) {
        this.mensaje = mensaje;
    }

    public String darMensaje() {
        return mensaje;
    }
}

